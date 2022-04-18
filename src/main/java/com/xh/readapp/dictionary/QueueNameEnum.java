package com.xh.readapp.dictionary;

public enum QueueNameEnum {
    ARTICLE_QUEUE_NAME("article.queue"),
    WARNING_QUEUE_NAME("warning.queue"),
    DATA_QUEUE_NAME("data.queue"),
    COMMENT_QUEUE_NAME("comment.queue"),
    HISTORY_QUEUE_NAME("history.queue");

    private String QUEUE_NAME;

    QueueNameEnum(String QUEUE_NAME) {
        this.QUEUE_NAME = QUEUE_NAME;
    }

    public String getQUEUE_NAME() {
        return QUEUE_NAME;
    }

    public void setQUEUE_NAME(String QUEUE_NAME) {
        this.QUEUE_NAME = QUEUE_NAME;
    }
}
