package com.sst.lld.atm.transaction;

import com.sst.lld.atm.model.enums.TransactionType;

public class TransactionFactory {
    private static final TransactionFactory INSTANCE = new TransactionFactory();

    private TransactionFactory() {}

    public static TransactionFactory getInstance() {
        return INSTANCE;
    }

    public Transaction getTransaction(TransactionType type) {
        return switch (type) {
            case WITHDRAW_CASH -> new WithdrawTransaction();
            case CHECK_BALANCE -> new BalanceInquiryTransaction();
            default -> throw new IllegalArgumentException("Invalid transaction type: " + type);
        };
    }
}