/*
 * Copyright (c) 2017 Kevin zhou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kevin.cleangank.model.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * HttpBuilder
 *
 * @author zwenkai@foxmail.com ,Created on 2017-08-10 22:37:35
 *         Major Function：<b>网络访问构造</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

final class ApiException<T extends Throwable> implements Consumer<T> {

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
