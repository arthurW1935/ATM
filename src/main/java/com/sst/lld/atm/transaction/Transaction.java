package com.sst.lld.atm.transaction;

import com.sst.lld.atm.ATMMachine;

public interface Transaction {
    void execute(ATMMachine machine, TransactionContext context);
}
