package com.sst.lld.atm.state;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.model.enums.TransactionType;

public class SelectOperationState implements ATMState {
    @Override
    public String getStateName() {
        return "Select Operation State";
    }

    @Override
    public void selectOperation(ATMMachine context, TransactionType type) {
        System.out.println("Operation selected: " + type);
        context.setSelectedTransaction(context.getTransactionFactory().getTransaction(type));
        context.setCurrentState(context.getStateFactory().createTransactionState());
    }
}