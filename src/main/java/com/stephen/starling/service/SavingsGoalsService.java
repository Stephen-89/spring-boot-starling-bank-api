package com.stephen.starling.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.stephen.starling.StarlingApiUrls;
import com.stephen.starling.config.HeadersConfig;
import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Amount;
import com.stephen.starling.pojo.SavingsGoal;
import com.stephen.starling.pojo.SavingsGoals;
import com.stephen.starling.pojo.requests.AmountRequest;
import com.stephen.starling.pojo.requests.SavingsGoalRequest;

@Service
public class SavingsGoalsService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HeadersConfig headersConfig;
	@Autowired
	private AccountService accountService;

    public SavingsGoals getSavingsGoals() throws StarlingException {
        // Replace "accountUID" with the actual account UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOALS.replace("accountUID", accountService.getAccountUid());
        // Make a GET request to retrieve savings goals
        SavingsGoals savingsGoals = this.restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(headersConfig.getHeaders()),
                new ParameterizedTypeReference<SavingsGoals>() {
                }).getBody();
        return savingsGoals;
    }
    
    public SavingsGoal getSavingsGoal(String savingsGoalUid) throws StarlingException {
        // Replace placeholder with actual account UID and savings goal UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOAL
                .replace("accountUID", accountService.getAccountUid())
                .replace("savingGoalUID", savingsGoalUid);
        // Make a GET request to retrieve the specified savings goal
        SavingsGoal savingsGoal = this.restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(headersConfig.getHeaders()),
                new ParameterizedTypeReference<SavingsGoal>() {
                }).getBody();
        return savingsGoal;
    }
    
    public String createSavingsGoal(SavingsGoalRequest savingsGoalRequest) throws StarlingException {
        // Replace placeholder with actual account UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOALS
                .replace("accountUID", accountService.getAccountUid());
        // Make a PUT request to create a new savings goal
        SavingsGoal savingsGoal = this.restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(savingsGoalRequest, headersConfig.getHeaders()),
                new ParameterizedTypeReference<SavingsGoal>() {
                }).getBody();
        // Return the UID of the newly created savings goal
        return savingsGoal.getSavingsGoalUid();
    }
    
    public String updateSavingsGoal(String savingsGoalUid, SavingsGoalRequest savingsGoalRequest)
            throws StarlingException {
        // Replace placeholders with actual account UID and savings goal UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOAL
                .replace("accountUID", accountService.getAccountUid())
                .replace("savingGoalUID", savingsGoalUid);
        // Make a PUT request to update the specified savings goal
        SavingsGoal savingsGoal = this.restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(savingsGoalRequest, headersConfig.getHeaders()),
                new ParameterizedTypeReference<SavingsGoal>() {
                }).getBody();
        // Return the UID of the updated savings goal
        return savingsGoal.getSavingsGoalUid();
    }
    
    public String addMoneyToSavingsGoal(String savingsGoalUid, AmountRequest amount) throws StarlingException {
        // Replace placeholders with actual account UID, savings goal UID, and transfer UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOAL_ADD_MONEY
                .replace("accountUID", accountService.getAccountUid())
                .replace("savingGoalUID", savingsGoalUid)
                .replace("transferUID", UUID.randomUUID().toString());
        // Make a PUT request to add money to the specified savings goal
        String responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(amount, headersConfig.getHeaders()), String.class).getBody();
        return responseEntity;
    }

    public String deleteSavingsGoal(String savingsGoalUid) throws StarlingException {
        // Replace placeholders with actual account UID and savings goal UID
        String url = StarlingApiUrls.STARLING_BANK_ACCOUNT_SAVING_GOAL
                .replace("accountUID", accountService.getAccountUid())
                .replace("savingGoalUID", savingsGoalUid);
        // Make a DELETE request to delete the specified savings goal
        String responseEntity = restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(headersConfig.getHeaders()), String.class).getBody();
        return responseEntity;
    }
    
    public void addToSavingsGoal(AmountRequest amountRequest) throws StarlingException {
        // Retrieve the existing savings goals
        SavingsGoals savingGoals = getSavingsGoals();
        // Initialize the savings goal UID
        String savingsGoalUid = "";
        // Check if there are existing savings goals
        if (!CollectionUtils.isEmpty(savingGoals.getSavingsGoalList())) {
            // If there are existing savings goals, select the first one
            savingsGoalUid = savingGoals.getSavingsGoalList().get(0).getSavingsGoalUid();
            // Add money to the selected savings goal
            addMoneyToSavingsGoal(savingsGoalUid, amountRequest);
        } else {
            // If there are no existing savings goals, create a new one
            SavingsGoalRequest savingsGoalRequest = createSavingsGoalRequest(amountRequest);
            createSavingsGoal(savingsGoalRequest);
        }
    }

	private SavingsGoalRequest createSavingsGoalRequest(AmountRequest amountRequest) {
		SavingsGoalRequest savingsGaolRequest = new SavingsGoalRequest("New Savings Account", "GBP",
				new Amount(amountRequest.getAmount().getCurrency(), amountRequest.getAmount().getMinorUnits()));
		return savingsGaolRequest;
	}

}
