package com.sst.lld.atm.transaction;

import com.sst.lld.atm.ATMMachine;
import com.sst.lld.atm.ATMInventory;
import com.sst.lld.atm.model.Account;
import com.sst.lld.atm.model.enums.CashType;

import java.util.Map;

public class WithdrawTransaction implements Transaction {
    @Override
    public void execute(ATMMachine machine, TransactionContext context) {
        Integer amount = context.get("amount");
        if (amount == null) {
            System.out.println("Transaction Failed: Amount not provided.");
            return;
        }

        Account account = machine.getCurrentAccount();
        ATMInventory inventory = machine.getAtmInventory();

        if (account.getBalance() < amount) {
            System.out.println("Transaction Failed: Insufficient funds in your account.");
            return;
        }

        Map<CashType, Integer> dispensePlan = inventory.createDispensePlan(amount);

        if (dispensePlan == null) {
            System.out.println("Transaction Failed: ATM cannot dispense the exact amount requested.");
            return;
        }

        // All checks passed, proceed with the transaction
        account.withdraw(amount);
        inventory.commitDispensePlan(dispensePlan);

        System.out.println("Withdrawal successful. Please collect your cash:");
        dispensePlan.forEach((cashType, count) ->
                System.out.println("- " + count + " x $" + cashType.value)
        );
    }
}