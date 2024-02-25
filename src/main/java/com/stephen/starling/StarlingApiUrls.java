package com.stephen.starling;

import com.stephen.starling.constants.Constants;

public final class StarlingApiUrls {

	//API
    public static final String STARLING_BANK_API = "https://api-sandbox.starlingbank.com/api/v2";

    //Accounts
    public static final String STARLING_BANK_ACCOUNTS = STARLING_BANK_API + "/accounts";

    //Savings Goals
    public static final String STARLING_BANK_ACCOUNT = STARLING_BANK_API + "/account";
    public static final String STARLING_BANK_ACCOUNT_SAVING_GOALS = STARLING_BANK_ACCOUNT + "/"+Constants.ACCOUNT_UID+"/savings-goals";
    public static final String STARLING_BANK_ACCOUNT_SAVING_GOAL = STARLING_BANK_ACCOUNT_SAVING_GOALS + "/"+Constants.SAVINGS_GOAL_UID;
    public static final String STARLING_BANK_ACCOUNT_SAVING_GOAL_ADD_MONEY = STARLING_BANK_ACCOUNT_SAVING_GOAL + "/add-money/"+Constants.TRANSFER_UID;

    //Transactions
    public static final String STARLING_BANK_ACCOUNT_TRANSACTIONS = STARLING_BANK_API + "/feed/account/"+Constants.ACCOUNT_UID+"/settled-transactions-between"
    		+ "?minTransactionTimestamp={minTransactionTimestamp}&maxTransactionTimestamp={maxTransactionTimestamp}";
    
}