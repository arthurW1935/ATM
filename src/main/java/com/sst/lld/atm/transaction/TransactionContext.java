package com.sst.lld.atm.transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionContext {
    private final Map<String, Object> data;

    private TransactionContext(Builder builder) {
        this.data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    private void put(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key) {
        if (data.containsKey(key)) {
            return (T) data.get(key);
        }
        return null;
    }

    public static class Builder {
        private final Map<String, Object> data = new HashMap<>();

        public Builder withAmount(int amount) {
            this.data.put("amount", amount);
            return this;
        }

        public Builder with(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public TransactionContext build() {
            return new TransactionContext(this);
        }
    }
}