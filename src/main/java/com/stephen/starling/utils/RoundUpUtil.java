package com.stephen.starling.utils;

import java.math.BigDecimal;
import java.util.List;

import com.stephen.starling.pojo.Transaction;

public final class RoundUpUtil {

    public static BigDecimal roundUpTransactions(List<Transaction> transactions) {
        BigDecimal rounding = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            // Round each transaction amount and add to the total rounding
            rounding = rounding.add(round(transaction.getAmount().getUnitsInCurrency()));
        }
        return rounding;
    }

    private static BigDecimal round(BigDecimal transaction) {
        // Calculate the fractional part of the transaction amount
        final BigDecimal fractionalPart = transaction.remainder(BigDecimal.ONE);
        // Subtract the fractional part from one to round up to the nearest whole number
        return BigDecimal.ONE.subtract(fractionalPart);
    }
}
