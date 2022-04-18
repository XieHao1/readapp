package com.xh.readapp.dictionary;

public enum ExchangeNameEnum {

    EXCHANGE_NAME("readApp"),
    BACKUP_EXCHANGE_NAME("backup.readApp");

    private String ExchangeName;

    ExchangeNameEnum(String exchangeName) {
        ExchangeName = exchangeName;
    }

    public String getExchangeName() {
        return ExchangeName;
    }

    public void setExchangeName(String exchangeName) {
        ExchangeName = exchangeName;
    }
}
