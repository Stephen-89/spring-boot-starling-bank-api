package com.stephen.starling.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stephen.starling.StarlingApiUrls;
import com.stephen.starling.config.HeadersConfig;
import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Transactions;
import com.stephen.starling.utils.DateUtil;

@Service
public class TransactionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HeadersConfig headersConfig;
	@Autowired
	private AccountService accountService;
	
    public Transactions transactionsBetween(String min, String max) throws StarlingException {
        // Create a map to hold the query parameters
        Map<String, String> params = new HashMap<>();
        // Set the minimum transaction timestamp parameter to the provided value or the current date minus 7 days
        params.put("minTransactionTimestamp", StringUtils.isNotEmpty(min) ? min : DateUtil.getCurrentDateMinusDays(7));
        // Set the maximum transaction timestamp parameter to the provided value or the current date
        params.put("maxTransactionTimestamp", StringUtils.isNotEmpty(max) ? max : DateUtil.getCurrentDate());
        // Replace placeholders with actual account UID and make a GET request to retrieve transactions
        Transactions transactions = this.restTemplate.exchange(
                StarlingApiUrls.STARLING_BANK_ACCOUNT_TRANSACTIONS
                        .replace("accountUID", accountService.getAccountUid()),
                HttpMethod.GET, new HttpEntity<>(headersConfig.getHeaders()),
                new ParameterizedTypeReference<Transactions>() {
                }, params).getBody();
        return transactions;
    }

}
