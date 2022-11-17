package org.margin.bank.http;

import okhttp3.Request;

import java.io.IOException;

/**
 * @ClassName HttpCall
 * @Author yyl
 * @Date 2022-03-13 17:10:15
 * @Description HttpCall
 * @Version 1.0
 */
public interface HttpCall<TResult> {

    /**
     * 同步执行并返回结果
     * @return
     * @throws IOException
     */
    TResult syncExecute() throws IOException;

    /**
     * 是否已执行
     * @return
     */
    Boolean isExecuted();

    /**
     * 取消
     */
    void cancel();

    /**
     * 是否已取消
     * @return
     */
    Boolean isCanceled();

    /**
     * 请求
     * @return
     */
    Request request();
}
