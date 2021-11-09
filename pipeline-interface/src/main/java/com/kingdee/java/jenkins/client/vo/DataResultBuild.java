package com.kingdee.java.jenkins.client.vo;


/**
* @author 高伟
* @date  2020/4/7 17:00
* @descrption
*/
public class DataResultBuild {

    public static <T> DataResult<T> success() {

        return new DataResult<T>().success();

    }

    public static <T> DataResult<T> success(T data) {
        return new DataResult<T>().success(data);

    }

    public static <T> DataResult<T> fail(String message) {
        return new DataResult<T>().fail(message);

    }

    public static <T> DataResult<T> fail(int status, String info, String message) {
        return new DataResult<T>().fail(status, info, message);

    }

}
