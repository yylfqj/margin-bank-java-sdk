package org.margin.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.margin.bank.custom.CustomTypeAdapter;
import org.margin.bank.custom.LocalDateTimeAdapter;
import org.margin.bank.http.BankHttpCall;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName AbstractMarginClient
 * @Author yyl
 * @Date 2022-03-15 16:32:46
 * @Description AbstractMarginClient
 * @Version 1.0
 */
public abstract class AbstractMarginClient<T> {

    public String agencyCode = "";

    public String hostname = "http://localhost:7838";

    public String privateKey = "";

    protected OkHttpClient okHttpClient = new OkHttpClient();

    public Gson json = getGson();

    /**
     * sdk版本号
     */
    protected String sdkVersion = "java:2.0.0";
    /**
     * 媒体类型
     */
    protected MediaType mediaType = MediaType.get("application/json");

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        Gson gson = gsonBuilder.create();

        try {
            Field factories = Gson.class.getDeclaredField("factories");
            factories.setAccessible(true);
            Object o = factories.get(gson);
            Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
            for (Class<?> c : declaredClasses) {
                if ("java.util.Collections.UnmodifiableList" == c.getName()) {
                    Field listField = c.getDeclaredField("list");
                    listField.setAccessible(true);
                    List<TypeAdapterFactory> list = (List<TypeAdapterFactory>)listField.get(o);
                    int i = list.indexOf(ObjectTypeAdapter.FACTORY);
                    list.set(i, CustomTypeAdapter.FACTORY);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson;
    }

    /**
     * get请求
     * @param url
     * @param typeToken
     * @param <TResult>
     * @return
     */
    protected <TResult> BankHttpCall<TResult> createHttpGetCall(String url, TypeToken<TResult> typeToken) {
        TypeAdapter<TResult> adapter = json.getAdapter(typeToken);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-oauth-sdk-version", sdkVersion)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        return new BankHttpCall<>(call, adapter);
    }

    /**
     * post请求
     * @param url
     * @param body
     * @param typeToken
     * @param <TResult>
     * @return
     */
    protected <TResult> BankHttpCall<TResult> createHttpPostCall(String url, String body, TypeToken<TResult> typeToken) {
        TypeAdapter<TResult> adapter = json.getAdapter(typeToken);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-oauth-sdk-version", sdkVersion)
                .post(RequestBody.create(body, mediaType))
                .build();

        Call call = okHttpClient.newCall(request);

        return new BankHttpCall<>(call, adapter);
    }

    /**
     * delete请求
     * @param url
     * @param typeToken
     * @param <TResult>
     * @return
     */
    protected <TResult> BankHttpCall<TResult> createHttpDeleteCall(String url, TypeToken<TResult> typeToken) {
        TypeAdapter<TResult> adapter = json.getAdapter(typeToken);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-oauth-sdk-version", sdkVersion)
                .delete()
                .build();

        Call call = okHttpClient.newCall(request);

        return new BankHttpCall<>(call, adapter);
    }

    /**
     * put请求
     * @param url
     * @param body
     * @param typeToken
     * @param <TResult>
     * @return
     */
    protected <TResult> BankHttpCall<TResult> createHttpPutCall(String url, String body, TypeToken<TResult> typeToken) {
        TypeAdapter<TResult> adapter = json.getAdapter(typeToken);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-oauth-sdk-version", sdkVersion)
                .put(RequestBody.create(body, mediaType))
                .build();

        Call call = okHttpClient.newCall(request);

        return new BankHttpCall<>(call, adapter);
    }

    @SuppressWarnings({"unchecked"})
    public T hostname(String hostname) {
        this.hostname = hostname;
        return (T) this;
    }

    @SuppressWarnings({"unchecked"})
    public T privateKey(String privateKey) {
        this.privateKey = privateKey;
        return (T) this;
    }

    @SuppressWarnings({"unchecked"})
    public T agencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
        return (T) this;
    }

    @SuppressWarnings({"unchecked"})
    public T build() {
        return (T) this;
    }
}
