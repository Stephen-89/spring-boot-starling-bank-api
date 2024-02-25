package com.stephen.starling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Transactions;
import com.stephen.starling.service.TransactionService;

@RestController
public class TransactionsController {

	@Autowired
	private TransactionService transactionService;

	/**
     * Retrieves transactions for the accounts within the specified time range.
     *
     * @param minTransactionTimestamp The minimum timestamp for transactions (optional).
     * @param maxTransactionTimestamp The maximum timestamp for transactions (optional).
     * @return The transactions for the accounts within the specified time range.
     * @throws StarlingException if an error occurs while retrieving the transactions.
     */
	@GetMapping("/accounts-transactions")
	public Transactions transactions(
			@RequestParam(name = "minTransactionTimestamp", required = false) String min,
			@RequestParam(name = "maxTransactionTimestamp", required = false) String max)
			throws StarlingException {
		return transactionService.transactionsBetween(min, max);
	}

}
