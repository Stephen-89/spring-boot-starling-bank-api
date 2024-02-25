package com.stephen.starling.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	private String feedItemUid;
	private String categoryUid;
	private Amount amount;
	private Amount sourceAmount;
	private String direction;
	private Date updatedAt;
	private Date transactionTime;
	private Date settlementTime;
	private String source;
	private String status;
	private String transactingApplicationUserUid;
	private String counterPartyType;
	private String counterPartyUid;
	private String counterPartyName;
	private String country;
	private String spendingCategory;
	private boolean hasAttachment;
	private boolean hasReceipt;
	private Object batchPaymentDetails;
}
