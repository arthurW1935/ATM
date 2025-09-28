package com.sst.lld.atm.state;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.model.Account;

public class HasCardState implements ATMState {
    @Override
    public String getStateName() {
        return "Has Card State (Awaiting PIN)";
    }

    @Override
    public void enterPin(ATMMachine context, int pin) {
        if (context.getCurrentCard().validatePin(pin)) {
            Account account = context.getAccount(context.getCurrentCard().getAccountNumber());
            if (account != null) {
                System.out.println("PIN validation successful.");
                context.setCurrentAccount(account);
                context.setCurrentState(context.getStateFactory().createSelectOperationState());
            } else {
                System.out.println("ERROR: Account not found for this card.");
                returnCard(context);
            }
        } else {
            System.out.println("ERROR: Invalid PIN entered.");
            returnCard(context);
        }
    }
}