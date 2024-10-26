package edu.northeastern.cs5200.ect.pojo;

public class Result<T> {
    private int status;
    private String message;
    private T data;

    // 构造函数
    public Result(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 快捷方法 - 成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    // 快捷方法 - 错误响应
    public static <T> Result<T> error(int status, String message) {
        return new Result<>(status, message, null);
    }

    // Getters 和 Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

