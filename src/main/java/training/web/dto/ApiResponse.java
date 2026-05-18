package training.web.dto;

import lombok.Data;
import training.web.Enums.ReturnCode;

@Data

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message, ReturnCode code, T data) {
        this.success = success;
        this.message = code.getMessage() + (message != null ? " - " + message : "");
        this.code = code.getCode();
        this.data = data;
    }

    // ===== Success =====
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, ReturnCode.SUCCESS, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, ReturnCode.SUCCESS, data);
    }

    // ===== Error =====
    public static <T> ApiResponse<T> error(ReturnCode code, String message) {
        return new ApiResponse<>(false, message, code, null);
    }

}
