package com.sst.lld.atm;

import com.sst.lld.atm.model.Account;
import com.sst.lld.atm.model.Card;
import com.sst.lld.atm.model.enums.TransactionType;
import com.sst.lld.atm.state.ATMState;
import com.sst.lld.atm.state.ATMStateFactory;
import com.sst.lld.atm.transaction.Transaction;
import com.sst.lld.atm.transaction.TransactionContext;
import com.sst.lld.atm.transaction.TransactionFactory;

import java.util.HashMap;
import java.util.Map;

public class ATMMachine {
    // State and data fields
    private ATMState currentState;
    private final ATMInventory atmInventory;
    private final Map<String, Account> accounts;

    // Session-specific data
    private Card currentCard;
    private Account currentAccount;
    private Transaction selectedTransaction;

    // Factories
    private final ATMStateFactory stateFactory;
    private final TransactionFactory transactionFactory;

    public ATMMachine() {
        this.stateFactory = ATMStateFactory.getInstance();
        this.transactionFactory = TransactionFactory.getInstance();
        this.currentState = stateFactory.createIdleState();
        this.atmInventory = new ATMInventory();
        this.accounts = new HashMap<>();
    }

    // --- USER ACTIONS (DELEGATED TO STATE) ---

    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void enterPin(int pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(TransactionType type) {
        currentState.selectOperation(this, type);
    }

    public void performTransaction(TransactionContext transactionContext) {
        currentState.performTransaction(this, transactionContext);
    }

    public void returnCard() {
        currentState.returnCard(this);
    }

    public void resetATM() {
        this.currentCard = null;
        this.currentAccount = null;
        this.selectedTransaction = null;
        this.currentState = stateFactory.createIdleState();
        System.out.println("ATM has been reset. Please insert your card.");
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
        System.out.println("ATM is now in: " + currentState.getStateName());
    }

    public ATMInventory getAtmInventory() {
        return atmInventory;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public ATMStateFactory getStateFactory() {
        return stateFactory;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }
}