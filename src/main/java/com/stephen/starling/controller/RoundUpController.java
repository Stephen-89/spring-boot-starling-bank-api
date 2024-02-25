package com.stephen.starling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.SavingsGoals;
import com.stephen.starling.service.RoundUpService;

@RestController
public class RoundUpController {
	
	@Autowired
	private RoundUpService roundUpService;
	
	/**
     * Rounds-up transactions for the accounts in a provided time period e.g. past week.
     *
     * @param minTransactionTimestamp The minimum timestamp for transactions (optional).
     * @param maxTransactionTimestamp The maximum timestamp for transactions (optional).
     * @return The savings goals associated with the accounts.
     * @throws StarlingException if an error occurs while processing the round-up transactions.
     */
	@GetMapping("/accounts-roundup")
	public SavingsGoals roundUp(
			@RequestParam(name = "minTransactionTimestamp", required = false) String min,
			@RequestParam(name = "maxTransactionTimestamp", required = false) String max) throws StarlingException {
		return roundUpService.roundUpFeedItems(min, max);
	}

}
