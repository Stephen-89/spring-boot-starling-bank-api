package com.stephen.starling.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Amount;
import com.stephen.starling.pojo.SavingsGoals;
import com.stephen.starling.pojo.Transaction;
import com.stephen.starling.pojo.Transactions;
import com.stephen.starling.pojo.requests.AmountRequest;
import com.stephen.starling.utils.RoundUpUtil;

@Service
public class RoundUpService {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private SavingsGoalsService savingGoalService;

	public SavingsGoals roundUpFeedItems(String min, String max) {
		try {
			// Retrieve transactions without specifying min and max timestamps to get all
			// transactions
			Transactions transactions = transactionService.transactionsBetween(
					StringUtils.isNotEmpty(min) ? min : StringUtils.EMPTY,
					StringUtils.isNotEmpty(max) ? max : StringUtils.EMPTY);
			// Check if there are feed items
			if (transactions != null && !CollectionUtils.isEmpty(transactions.getFeedItems())) {
				// Process the transactions to extract feed items
				List<Transaction> feedItems = processTransactions(transactions);
				// Round up the total amount of feed items
				BigDecimal transactionAmount = RoundUpUtil.roundUpTransactions(feedItems);
				// Create an amount request for the rounded-up amount
				AmountRequest amountRequest = createAmountRequest(transactionAmount);
				// Add the rounded-up amount to a savings goal
				savingGoalService.addToSavingsGoal(amountRequest);
			}
			// Return the updated savings goals
			return savingGoalService.getSavingsGoals();
		} catch (StarlingException exception) {
			exception.printStackTrace();
			throw new RuntimeException("Error fetching data");
		}
	}

	private List<Transaction> processTransactions(Transactions transactions) {
		// Filter transactions to select feed items with incoming amounts greater than
		// zero
		List<Transaction> feedItems = transactions.getFeedItems().stream()
				.filter(tr -> tr.getDirection().equalsIgnoreCase("IN")
						&& tr.getAmount().getMinorUnits().compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.toList());
		return feedItems;
	}

	private AmountRequest createAmountRequest(BigDecimal transactionAmount) {
		return new AmountRequest(new Amount("GBP", transactionAmount.movePointRight(2)));
	}

}
