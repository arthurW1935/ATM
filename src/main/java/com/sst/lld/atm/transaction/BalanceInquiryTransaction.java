package com.sst.lld.atm.transaction;

import com.sst.lld.atm.ATMMachine;

public class BalanceInquiryTransaction implements Transaction {
    @Override
    public void execute(ATMMachine machine, TransactionContext context) {
        double balance = machine.getCurrentAccount().getBalance();
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }
}