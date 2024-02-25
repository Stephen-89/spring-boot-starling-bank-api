package com.stephen.starling.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account{
    private String accountUid;
    private String accountType;
    private String defaultCategory;
    private String currency;
    private Date createdAt;
    private String name;
}
