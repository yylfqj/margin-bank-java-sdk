package org.margin.bank.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.margin.bank.AbstractMarginClient;
import org.margin.bank.custom.CustomTypeAdapter;
import org.margin.bank.http.BankHttpCall;

import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @ClassName ManagerMarginClient
 * @Author yyl
 * @Date 2022-03-12 17:19:08
 * @Description ManagerMarginClient
 * @Version 1.0
 */
public class ManagerMarginClient extends AbstractMarginClient<ManagerMarginClient> {

    public ManagerMarginClient(String agencyCode, String privateKey) {
        this.agencyCode = agencyCode;
        this.privateKey = privateKey;
    }

    /**
     * get请求
     * @param url
     * @param typeToken
     * @param <TResult>
     * @return
     */
    @Override
    protected <TResult> BankHttpCall<TResult> createHttpGetCall(String url, TypeToken<TResult> typeToken) {
        return super.createHttpGetCall(url, typeToken);
    }

    /**
     * post请求
     * @param url
     * @param body
     * @param typeToken
     * @param <TResult>
     * @return
     */
    @Override
    protected <TResult> BankHttpCall<TResult> createHttpPostCall(String url, String body, TypeToken<TResult> typeToken) {
        return super.createHttpPostCall(url, body, typeToken);
    }

    /**
     * delete请求
     * @param url
     * @param typeToken
     * @param <TResult>
     * @return
     */
    @Override
    protected <TResult> BankHttpCall<TResult> createHttpDeleteCall(String url, TypeToken<TResult> typeToken) {
        return super.createHttpDeleteCall(url, typeToken);
    }

    /**
     * put请求
     * @param url
     * @param body
     * @param typeToken
     * @param <TResult>
     * @return
     */
    @Override
    protected <TResult> BankHttpCall<TResult> createHttpPutCall(String url, String body, TypeToken<TResult> typeToken) {
        return super.createHttpPutCall(url, body, typeToken);
    }

    /**
     * 对象转化拼接字符串
     * @param obj
     * @return
     */
    protected String objectToQueryString(Object obj) {
        if (obj == null) {
            return "";
        }
        String json = this.json.toJson(obj);
        //自定义gson解析
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(CustomTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        Map map = gson.fromJson(json, Map.class);
        StringJoiner sj = new StringJoiner("&");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            sj.add(key + "=" + value);
        }
        return sj.toString();
    }

    /**
     * 获取流水客户端
     * @return
     */
    public FlowManagerClient flowManagerClient() {
        return new FlowManagerClient(this);
    }
}
