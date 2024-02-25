package com.stephen.starling.pojo.requests;

import com.stephen.starling.pojo.Amount;

import lombok.Data;

@Data
public class SavingsGoalRequest{
	
    private String name;
    private String currency;
    private Amount target;
    private String base64EncodedPhoto;
    
	public SavingsGoalRequest(String name, String currency, Amount target) {
		this.name = name;
		this.currency = currency;
		this.target = target;
	}

}
