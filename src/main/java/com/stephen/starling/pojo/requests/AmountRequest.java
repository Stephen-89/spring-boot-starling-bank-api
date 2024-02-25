package com.stephen.starling.pojo.requests;

import com.stephen.starling.pojo.Amount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountRequest {
	private Amount amount;
}
