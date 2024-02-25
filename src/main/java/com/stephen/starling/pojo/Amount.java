package com.stephen.starling.pojo;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Amount {
	
	private String currency;
	private BigDecimal minorUnits;

    public BigDecimal getUnitsInCurrency() {
        if (Objects.isNull(minorUnits)) {
        	return BigDecimal.ZERO;
        }
        return minorUnits.movePointLeft(2);
    }
    
}
