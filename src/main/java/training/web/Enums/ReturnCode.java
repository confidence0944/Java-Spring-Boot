package training.web.Enums;

public enum ReturnCode {

    SUCCESS(200, "執行成功"),
    BAD_REQUEST(400, "請求參數錯誤"),
    VALIDATION_FAILED(422, "參數驗證失敗"),
    CONFLICT(409, "資源衝突"),
    NOT_FOUND(404, "查無資料"),
    UNKNOWN_ERROR(500, "系統發生未知錯誤");

    private final int code;
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
