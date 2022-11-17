package org.margin.bank.exceptions;

/**
 * @ClassName BankExceptionEnum
 * @Author yyl
 * @Date 2022-04-07 19:17:48
 * @Description BankExceptionEnum
 * @Version 1.0
 */
public enum BankExceptionEnum {

    LOCAL_CHECK_DATA_ERROR("S00000", "本地数据校验失败"),
    BAD_RESULT("S00001", "错误的返回值"),
    ;

    public final String code;

    public final String message;

    BankExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
