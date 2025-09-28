package com.sst.lld.atm.state;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.transaction.Transaction;
import com.sst.lld.atm.transaction.TransactionContext;

public class TransactionState implements ATMState {
    @Override
    public String getStateName() {
        return "Transaction State";
    }

    @Override
    public void performTransaction(ATMMachine machine, TransactionContext transactionContext) {
        Transaction transaction = machine.getSelectedTransaction();
        if (transaction != null) {
            transaction.execute(machine, transactionContext);
        } else {
            System.out.println("ERROR: No transaction was selected.");
        }

        machine.setSelectedTransaction(null);
        machine.setCurrentState(machine.getStateFactory().createSelectOperationState());
    }
}