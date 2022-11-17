package org.margin.bank.exceptions;

/**
 * @ClassName BankException
 * @Author yyl
 * @Date 2022-04-07 17:13:32
 * @Description BankException
 * @Version 1.0
 */
public class BankException extends RuntimeException{

    private static final long serialVersionUID = -6239564470823577064L;

    /**
     * 具体异常码
     */
    protected String code;

    /**
     * 异常信息
     */
    protected String msg;

    public BankException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BankException(BankExceptionEnum bankExceptionEnum) {
        super(bankExceptionEnum.message);
        this.code = bankExceptionEnum.code;
        this.msg = bankExceptionEnum.message;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
