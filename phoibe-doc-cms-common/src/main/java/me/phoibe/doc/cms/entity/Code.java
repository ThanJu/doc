package me.phoibe.doc.cms.entity;

public enum Code {
    SYSTEM_ERROR(1000,"系统异常"),
    SUCCESS(1,"成功"),
    FAILED(2,"失败"),
    LOGIN_FAILED(2001,"登录失败"),
    ;

    Code(int code, String value){
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;

    @Override
    public String toString() {
        return "Code{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }
}
