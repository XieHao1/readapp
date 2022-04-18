package com.xh.readapp.dictionary;

public enum ErrorEnum {

    SYSTEM_ERROR(10000,"系统异常 "),
    LOGIN_ERROR(10001,"登录失败"),
    SIGNATURE_ERROR(10002,"微信签名错误"),
    TOKEN_SIGN_ERROR(10003,"token签名错误"),
    TOKEN_EXPIRED_ERROR(10004,"token过期"),
    TOKEN_ALGORITHM_ERROR(10005,"算法不匹配"),
    TOKEN_INVALID_ERROR(10006,"payload失效"),
    INSERT_HISTORY_ERROR(10007,"记录失败"),
    ALREADY_LIKE_COUNTS_ERROR(10008,"已点赞"),
    NOT_LIKE_COUNTS_ERROR(10009,"未点赞"),
    INSERT_COMMENT_ERROR(10010,"评论失败"),
    UPLOAD_IMAGE_ERROR(10011,"图片上传失败"),
    UPDATE_ARTICLE_ERROR(10012,"修改文章失败"),
    INSERT_FOCUS_ERROR(10013,"关注失败"),
    DELETE_FOCUS_ERROR(10014,"取消关注失败");

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
