package com.sst.lld.atm.state;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.model.Card;
import com.sst.lld.atm.model.enums.TransactionType;
import com.sst.lld.atm.transaction.TransactionContext;

public interface ATMState {
    String getStateName();

    default void insertCard(ATMMachine context, Card card) {
        System.out.println("ERROR: Operation not allowed in " + getStateName());
    }

    default void enterPin(ATMMachine context, int pin) {
        System.out.println("ERROR: Operation not allowed in " + getStateName());
    }

    default void selectOperation(ATMMachine context, TransactionType type) {
        System.out.println("ERROR: Operation not allowed in " + getStateName());
    }

    default void performTransaction(ATMMachine context, TransactionContext transactionContext) {
        System.out.println("ERROR: Operation not allowed in " + getStateName());
    }

    default void returnCard(ATMMachine context) {
        System.out.println("Returning card...");
        context.resetATM();
    }
}