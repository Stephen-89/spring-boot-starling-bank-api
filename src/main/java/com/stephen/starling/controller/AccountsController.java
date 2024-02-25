package com.stephen.starling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.starling.exception.StarlingException;
import com.stephen.starling.pojo.Accounts;
import com.stephen.starling.service.AccountService;

@RestController
public class AccountsController {

	@Autowired
	private AccountService accountService;
	
    /**
     * Retrieves the list of accounts.
     *
     * @return The list of accounts.
     * @throws StarlingException if an error occurs while retrieving the accounts.
     */
	@GetMapping("/accounts")
	public Accounts accounts() throws StarlingException {
		return accountService.accounts();
	}

}