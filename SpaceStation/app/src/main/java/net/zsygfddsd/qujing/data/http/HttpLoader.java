package net.zsygfddsd.qujing.data.http;

import android.content.Context;
import android.util.Log;

import net.zsygfddsd.qujing.data.local.SpCache;
import net.zsygfddsd.qujing.data.local.SpCacheKey;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 16/7/19.
 */
public class HttpLoader implements HttpContract {

    private static final String BaseUrl = "http://gank.io/api/";

    private static final int DEFAULT_TIMEOUT = 10;
    private final OkHttpClient.Builder okhttpBuiler;
    private final Retrofit.Builder retrofitBuilder;

    private Retrofit retrofit;

    private HttpLoader() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("okhttp", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttpBuiler = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging);

        retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }


    public static HttpLoader getInstance(Context context, boolean needCookie) {
        final Context ct = context.getApplicationContext();
        HttpLoader httpLoader = HttpLoaderHolder.Instance;
        if (needCookie) {
            httpLoader.okhttpBuiler.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    //替换
                    builder.header("Cookie", (String) SpCache.get(ct, SpCacheKey.Cookie, "-1"));
                    return chain.proceed(builder.build());
                }
            });
        }
        httpLoader.retrofit = httpLoader.retrofitBuilder
                .baseUrl(BaseUrl)
                .client(httpLoader.okhttpBuiler.build())
                .build();
        Log.e("http", httpLoader.toString());
        return httpLoader;
    }

    //设计模式推荐的内部静态类实现的单例模式
    private static class HttpLoaderHolder {
        private static final HttpLoader Instance = new HttpLoader();
    }


    private <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    @Override
    public WelfareService welfareHttp() {
        return createService(WelfareService.class);
    }
}
