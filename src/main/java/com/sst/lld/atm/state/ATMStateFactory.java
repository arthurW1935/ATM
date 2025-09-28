package com.sst.lld.atm.state;

public class ATMStateFactory {
    private static final ATMStateFactory INSTANCE = new ATMStateFactory();

    private ATMStateFactory() {}

    public static ATMStateFactory getInstance() {
        return INSTANCE;
    }

    public ATMState createIdleState() {
        return new IdleState();
    }

    public ATMState createHasCardState() {
        return new HasCardState();
    }

    public ATMState createSelectOperationState() {
        return new SelectOperationState();
    }

    public ATMState createTransactionState() {
        return new TransactionState();
    }
}