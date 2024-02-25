package com.stephen.starling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stephen.starling.pojo.Amount;
import com.stephen.starling.pojo.Transaction;
import com.stephen.starling.utils.RoundUpUtil;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundUpServiceTests {
	
    @Mock
    private Transaction transaction1;
    @Mock
    private Transaction transaction2;
    @Mock
    private Transaction transaction3;

    @Test
    public void testRoundUpTransactions() {
    	
    	transaction1 = mockTransaction(new BigDecimal("435"));
    	transaction2 = mockTransaction(new BigDecimal("520"));
    	transaction3 = mockTransaction(new BigDecimal("087"));

        // Creating a list of transactions
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Calling the method under test
        BigDecimal roundedAmount = RoundUpUtil.roundUpTransactions(transactions);

        // Asserting the result with a descriptive message
        BigDecimal expectedRoundedAmount = new BigDecimal("1.58");
        assertEquals(expectedRoundedAmount, roundedAmount, "The rounded amount should be " + expectedRoundedAmount);
        
    }
    
    private Transaction mockTransaction(BigDecimal value) {
    	Amount amount = new Amount("GBP", value);
    	Transaction transaction = new Transaction();
    	transaction.setAmount(amount);
    	transaction.setSourceAmount(amount);
    	return transaction;
    }

}