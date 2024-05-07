package com.example.ca4u.apiResponse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "message", "results"})
public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T result;

    /**
     * ApiResponse 생성자
     * 인스턴스화는 오직 정적 팩토리 메소드를 통해서만 수행합니다.
     */
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(true,"요청에 성공했습니다.",null);
    }

    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(true,"요청에 성공했습니다.",result);
    }

    public static <T> ApiResponse<T> ok(T result, String message) {
        return new ApiResponse<>(true, message,result);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message,null);
    }
}
