package com.stephen.starling.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stephen.starling.StarlingApiUrls;
import com.stephen.starling.cache.AccountUidCacheManager;
import com.stephen.starling.config.HeadersConfig;
import com.stephen.starling.constants.Constants;
import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Accounts;

@Service
public class AccountService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HeadersConfig headersConfig;
	@Autowired
	private AccountUidCacheManager accountUidCacheManager;

	public Accounts accounts() throws StarlingException {
	    // Perform a GET request to retrieve the list of accounts
	    Accounts accounts = this.restTemplate.exchange(StarlingApiUrls.STARLING_BANK_ACCOUNTS, HttpMethod.GET,
	            new HttpEntity<>(headersConfig.getHeaders()), new ParameterizedTypeReference<Accounts>() {
	            }).getBody();
	    // Cache the account UID of the first account
	    accountUidCacheManager.put(Constants.ACCOUNT_UID, accounts.getAccounts().get(0).getAccountUid());
	    return accounts;
	}

	public String getAccountUid() throws StarlingException {
	    // Attempt to retrieve the account UID from the cache
	    String accountUID = accountUidCacheManager.get(Constants.ACCOUNT_UID);
	    if (StringUtils.isNotEmpty(accountUID)) {
	        // If the account UID is cached, return it
	        return accountUID;
	    } else {
	        // If the account UID is not cached, fetch it using the accounts() method and cache it
	        return this.accounts().getAccounts().get(0).getAccountUid();
	    }
	}
	
}
