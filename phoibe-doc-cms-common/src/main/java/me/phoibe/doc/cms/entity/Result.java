package me.phoibe.doc.cms.entity;

import java.io.Serializable;

/**
 * 返回值
 *
 * @author tengzhaolei
 * @Title: Result
 * @Description: class
 * @date 2018/8/23 1:09
 */
public class Result<D> implements Serializable {
    private Code code;

    /**
     * 数据
     */
    private D data;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public Result(Code code, D data){
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {
        Result<String> result = new Result<>(Code.SYSTEM_ERROR, "test");
        System.out.printf(result.toString());
    }

}
