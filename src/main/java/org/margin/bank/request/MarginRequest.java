package org.margin.bank.request;

/**
 * @ClassName MarginRequest
 * @Author yyl
 * @Date 2022-10-27 20:52:27
 * @Description MarginRequest
 * @Version 1.0
 */
public class MarginRequest<T> {

    private String agencyCode;

    private String sign;

    private String timestamp;

    private T data;

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
