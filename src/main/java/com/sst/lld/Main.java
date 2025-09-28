package com.sst.lld;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.model.Account;
import com.sst.lld.atm.model.Card;
import com.sst.lld.atm.model.enums.TransactionType;
import com.sst.lld.atm.transaction.TransactionContext;


public class Main {
    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();

        atm.addAccount(new Account("ACC101", 2500.0));
        atm.addAccount(new Account("ACC102", 500.0));

        Card userCard = new Card("CARD001", 1234, "ACC101");
        Card anotherCard = new Card("CARD002", 9876, "ACC102");

        System.out.println("--- Scenario 1: Successful Withdrawal ---");
        System.out.println("ATM total cash before: $" + atm.getAtmInventory().getTotalCash());

        atm.insertCard(userCard);
        atm.enterPin(1234);
        atm.selectOperation(TransactionType.WITHDRAW_CASH);
        atm.performTransaction(TransactionContext.builder()
                                            .withAmount(280)
                                            .build());
        atm.selectOperation(TransactionType.CHECK_BALANCE);
        atm.performTransaction(TransactionContext.builder()
                                            .build());
        atm.returnCard();

        System.out.println("\nATM total cash after: $" + atm.getAtmInventory().getTotalCash());
        System.out.println("------------------------------------------");

        System.out.println("\n--- Scenario 2: Invalid PIN ---");
        atm.insertCard(anotherCard);
        atm.enterPin(1111); // Incorrect PIN
        atm.returnCard();
        System.out.println("----------------------------------");
    }
}