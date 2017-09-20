package com.kevin.cleangank.model.net;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.kevin.cleangank.model.app.CleanGank;
import com.kevin.cleangank.model.app.ConfigKeys;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import timber.log.Timber;

/**
 * Created by zhouwenkai on 2017/8/9.
 */

public class HttpBuilder {

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 30;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = CleanGank.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            boolean isReleased = CleanGank.getConfiguration(ConfigKeys.IS_RELEASED);
            if (!isReleased) {
                HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(
                        new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Timber.tag("HttpLogging").i(message);
                            }
                        }
                );
                loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                BUILDER.addInterceptor(loggerInterceptor);
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = CleanGank.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Service接口
     */
    private static final class APIServiceHolder {
        private static final APIService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(APIService.class);
    }

    public static APIService getRestService() {
        return APIServiceHolder.REST_SERVICE;
    }

}
