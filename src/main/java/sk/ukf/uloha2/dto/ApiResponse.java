package sk.ukf.uloha2.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiResponse<T> {

    private String message;
    private T data;
    private String datetime;

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public ApiResponse(T data, String message) {
        this.message = message;
        this.data = data;
        this.datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data,null);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data,message);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null,message);
    }
}
