package org.margin.bank.response;

/**
 * @ClassName MarginResponse
 * @Author yyl
 * @Date 2022-10-27 20:52:13
 * @Description MarginResponse
 * @Version 1.0
 */
public class MarginResponse<T> {

    private Integer code;
    private String message;
    private String errorCode;
    private String errorMessage;
    private boolean success;
    private T result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
