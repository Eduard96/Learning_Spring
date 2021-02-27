package com.example.GodLovesTheTrinity;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TransactionInfo {
    public String atm_id = null;
    public String pin = null;
    public Long CARD_NUMBER = null;
    public HashMap<String, String> card_info = null;
    public String newPin = null;
    public Long cardNumber = null;
    public Long customerID = null;
    public Long accountNumber = null;
    public String currency = null;
    public BigDecimal amount = null;
    public String includeBalances = null;
    public String from = null;
    public String to = null;

    @Ignore
    private static final int ATM_ID_MAX_LENGTH = 100;
    @Ignore
    private static final Long MIN_ACCOUNT_NUMBER = Long.valueOf("1000000000000000");
    @Ignore
    private static final Long MAX_ACCOUNT_NUMBER = Long.valueOf("9999999999999999");
    @Ignore
    private static final Long MIN_CARD_NUMBER = Long.valueOf("1000000000000");
    @Ignore
    private static final Long MAX_CARD_NUMBER = Long.valueOf("9999999999999999");
    @Ignore
    private static final int MIN_PIN_LENGTH = 4;
    @Ignore
    private static final int MAX_PIN_LENGTH = 6;

    public String getAtm_id() {
        return atm_id;
    }

    public String getPin() {
        return pin;
    }

    public Long getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public HashMap<String, String> getCard_info() {
        return card_info;
    }

    public String getNewPin() {
        return newPin;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getIncludeBalances() {
        return includeBalances;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
