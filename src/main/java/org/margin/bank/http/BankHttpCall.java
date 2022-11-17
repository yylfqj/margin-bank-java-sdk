package org.margin.bank.http;

import com.google.gson.TypeAdapter;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.margin.bank.exceptions.BankException;
import org.margin.bank.exceptions.BankExceptionEnum;

import java.io.IOException;

/**
 * @ClassName BankHttpCall
 * @Author yyl
 * @Date 2022-03-13 21:14:15
 * @Description BankHttpCall
 * @Version 1.0
 */
public class BankHttpCall<TResult> implements HttpCall<TResult> {

    private final Call call;

    private final TypeAdapter<TResult> adapter;

    public BankHttpCall(Call call, TypeAdapter<TResult> adapter) {
        this.call = call;
        this.adapter = adapter;
    }

    @Override
    public TResult syncExecute() throws IOException {
        Response response = call.execute();

        // 处理返回数据
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new BankException(BankExceptionEnum.BAD_RESULT);
            }
            String body = responseBody.string();
            TResult data = adapter.fromJson(body);
            return data;
        } else {
            String error = "";
            if (response.body() != null) {
               error = response.body().string();
            }
            throw new IOException("Unexpected code " + error);
        }
    }

    @Override
    public Boolean isExecuted() {
        return call.isExecuted();
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public Boolean isCanceled() {
        return call.isCanceled();
    }

    @Override
    public Request request() {
        return call.request();
    }

}
