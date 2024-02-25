package com.stephen.starling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.SavingsGoals;
import com.stephen.starling.pojo.SavingsGoal;
import com.stephen.starling.pojo.requests.AmountRequest;
import com.stephen.starling.pojo.requests.SavingsGoalRequest;
import com.stephen.starling.service.SavingsGoalsService;

@RestController
public class SavingsGoalsController {
	
	@Autowired
	private SavingsGoalsService savingsGoalsService;

	/**
     * Retrieves the savings goals associated with the accounts.
     *
     * @return The savings goals associated with the accounts.
     * @throws StarlingException if an error occurs while retrieving the savings goals.
     */
	@GetMapping("/accounts-savings-goals")
	public SavingsGoals accountsSavingGoals() throws StarlingException {
		return savingsGoalsService.getSavingsGoals();
	}


    /**
     * Retrieves a specific savings goal by its UID.
     *
     * @param savingsGoalUid The UID of the savings goal to retrieve.
     * @return The savings goal with the specified UID.
     * @throws StarlingException if an error occurs while retrieving the savings goal.
     */
	@GetMapping("/accounts-savings-goal")
	public SavingsGoal accountsSavingGoal(@RequestParam(name = "savingsGaolUid") String savingsGaolUid) throws StarlingException {
		return savingsGoalsService.getSavingsGoal(savingsGaolUid);
	}


    /**
     * Creates a new savings goal.
     *
     * @param savingsGoalRequest The request containing the details of the savings goal to be created.
     * @return The UID of the savings goal
     * @throws StarlingException if an error occurs while creating the savings goal.
     */
	@PutMapping("/accounts-savings-goal-create")
	public String createSavingGoal(@RequestBody SavingsGoalRequest savingsGaolRequest) throws StarlingException {
		return savingsGoalsService.createSavingsGoal(savingsGaolRequest);
	}


    /**
     * Updates an existing savings goal.
     *
     * @param savingsGoalUid The UID of the savings goal to be updated.
     * @param savingsGoalRequest The request containing the updated details of the savings goal.
     * @return The UID of the savings goal.
     * @throws StarlingException if an error occurs while updating the savings goal.
     */
	@PutMapping("/accounts-savings-goal-update")
	public String updateSavingGoal(@RequestParam(name = "savingsGaolUid") String savingsGaolUid,
			@RequestBody SavingsGoalRequest savingsGaolRequest) throws StarlingException {
		return savingsGoalsService.updateSavingsGoal(savingsGaolUid, savingsGaolRequest);
	}

    /**
     * Adds money to a savings goal.
     *
     * @param savingsGoalUuid The UID of the savings goal to add money to.
     * @param amount The amount to add to the savings goal.
     * @return A message indicating the success of the operation.
     * @throws StarlingException if an error occurs while adding money to the savings goal.
     */
	@PutMapping("/accounts-savings-goal-add-money")
	public String addMoneySavingGoal(@RequestParam(name = "savingsGaolUid") String savingsGaolUid,
			@RequestBody AmountRequest amount) throws StarlingException {
		return savingsGoalsService.addMoneyToSavingsGoal(savingsGaolUid, amount);
	}


    /**
     * Deletes a savings goal.
     *
     * @param savingsGoalUid The UID of the savings goal to delete.
     * @return A message indicating the success of the operation.
     * @throws StarlingException if an error occurs while deleting the savings goal.
     */
	@DeleteMapping("/accounts-savings-goal-delete")
	public String deleteSavingGoal(@RequestParam(name = "savingsGaolUid") String savingsGaolUid) throws StarlingException {
		return savingsGoalsService.deleteSavingsGoal(savingsGaolUid);
	}

}
