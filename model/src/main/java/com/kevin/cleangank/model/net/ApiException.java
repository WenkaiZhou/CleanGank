package com.kevin.cleangank.model.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by zhouwenkai on 2017/8/10.
 */

public class ApiException<T extends Throwable> implements Consumer<T> {

    private static final String TAG = "ApiException";

    private static final String SOCKET_TIME_OUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";

    private Consumer<? super Throwable> onError;

    public ApiException(Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    /**
     * Consume the given value.
     *
     * @param t the value
     * @throws Exception on error
     */
    @Override
    public void accept(T t) throws Exception {
        if (t instanceof SocketTimeoutException) {
            Timber.tag(TAG).e("onError: SocketTimeoutException " + SOCKET_TIME_OUT_EXCEPTION);
            onError.accept(new Throwable(SOCKET_TIME_OUT_EXCEPTION));
        } else if (t instanceof ConnectException) {
            Timber.tag(TAG).e(TAG, "onError: ConnectException " + CONNECT_EXCEPTION);
            onError.accept(new Throwable(CONNECT_EXCEPTION));
        } else if (t instanceof UnknownHostException) {
            Timber.tag(TAG).e(TAG, "onError: UnknownHostException " + UNKNOWN_HOST_EXCEPTION);
            onError.accept(new Throwable(UNKNOWN_HOST_EXCEPTION));
        } else {
            Timber.tag(TAG).e(TAG, "onError: " + t.getMessage());
            onError.accept(t);
        }
    }
}
