package com.sst.lld.atm.state;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.model.Card;

public class IdleState implements ATMState {
    @Override
    public String getStateName() {
        return "Idle State";
    }

    @Override
    public void insertCard(ATMMachine context, Card card) {
        System.out.println("Card has been inserted.");
        context.setCurrentCard(card);
        context.setCurrentState(context.getStateFactory().createHasCardState());
    }

    @Override
    public void returnCard(ATMMachine context) {
        System.out.println("No card to return.");
    }
}