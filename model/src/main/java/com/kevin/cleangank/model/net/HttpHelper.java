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

import android.support.annotation.NonNull;

import com.kevin.cleangank.model.entity.HttpResult;
import com.kevin.cleangank.model.util.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * ResponseTransform
 *
 * @author zwenkai@foxmail.com ,Created on 2017-08-10 23:24:18
 *         Major Function：<b>数据实体转化</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public class HttpHelper {

    public static <T> Observable<T> request(
            @NonNull final CompositeDisposable subscriptions,
            @NonNull final Observable<HttpResult<T>> observable) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<T> e) throws Exception {
                Disposable disposable = observable
                        .map(ResponseTransform.getInstance())
                        .compose(RxSchedulers.io2main())
                        .subscribe(
                                new Consumer<T>() {

                                    @Override
                                    public void accept(T t) throws Exception {
                                        e.onNext(t);
                                        e.onComplete();
                                    }
                                },
                                new ApiException(e),
                                new Action() {

                                    @Override
                                    public void run() throws Exception {

                                    }
                                });
                subscriptions.add(disposable);
            }
        });
    }

    public static APIService api() {
        return HttpBuilder.getRestService();
    }
}
