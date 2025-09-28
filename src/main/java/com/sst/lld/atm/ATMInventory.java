package com.sst.lld.atm;

import com.sst.lld.atm.model.enums.CashType;
import java.util.EnumMap;
import java.util.Map;

public class ATMInventory {
    private final Map<CashType, Integer> cashInventory;

    public ATMInventory() {
        cashInventory = new EnumMap<>(CashType.class);
        initializeInventory();
    }

    private void initializeInventory() {
        cashInventory.put(CashType.BILL_100, 10);
        cashInventory.put(CashType.BILL_50, 10);
        cashInventory.put(CashType.BILL_20, 20);
        cashInventory.put(CashType.BILL_10, 30);
        cashInventory.put(CashType.BILL_5, 20);
        cashInventory.put(CashType.BILL_1, 50);
    }

    public int getTotalCash() {
        return cashInventory.entrySet().stream()
                .mapToInt(entry -> entry.getKey().value * entry.getValue())
                .sum();
    }

    public Map<CashType, Integer> createDispensePlan(int amount) {
        if (getTotalCash() < amount) {
            return null;
        }

        Map<CashType, Integer> dispensePlan = new EnumMap<>(CashType.class);
        int remainingAmount = amount;

        for (CashType cashType : CashType.values()) {
            int billsAvailable = cashInventory.get(cashType);
            int billsNeeded = remainingAmount / cashType.value;
            int billsToDispense = Math.min(billsAvailable, billsNeeded);

            if (billsToDispense > 0) {
                dispensePlan.put(cashType, billsToDispense);
                remainingAmount -= billsToDispense * cashType.value;
            }
        }

        return remainingAmount == 0 ? dispensePlan : null;
    }

    public void commitDispensePlan(Map<CashType, Integer> dispensePlan) {
        dispensePlan.forEach((cashType, count) ->
                cashInventory.merge(cashType, -count, Integer::sum)
        );
    }
}