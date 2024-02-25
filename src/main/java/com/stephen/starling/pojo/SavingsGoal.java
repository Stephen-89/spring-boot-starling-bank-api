package com.stephen.starling.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsGoal{
	private String savingsGoalUid;
    private String name;
    private Amount target;
    private Amount totalSaved;
    private Double savedPercentage; 
    private String state;
}
