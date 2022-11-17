package org.margin.bank.request;

import java.io.Serializable;

/**
 * @ClassName ResultData
 * @Author yyl
 * @Date 2022-04-25 14:59:16
 * @Description ResultData
 * @Version 1.0
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID = -3970406394529280188L;

    private String id;

    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
