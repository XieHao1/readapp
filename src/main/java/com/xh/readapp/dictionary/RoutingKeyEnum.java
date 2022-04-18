package com.xh.readapp.dictionary;

public enum RoutingKeyEnum {

    ARTICLE_ROUTING_KEY("article"),
    DATA_ROUTING_KEY("data"),
    COMMENT_ROUTING_KEY("comment"),
    HISTORY_ROUTING_KEY("history");

    private String routingKey;

    RoutingKeyEnum(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
