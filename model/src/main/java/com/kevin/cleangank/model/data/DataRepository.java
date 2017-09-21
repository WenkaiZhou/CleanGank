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
package com.kevin.cleangank.model.data;

import com.kevin.cleangank.model.entity.PrettyGirl;
import com.kevin.cleangank.model.net.HttpBuilder;
import com.kevin.cleangank.model.net.ResponseTransform;
import com.kevin.cleangank.model.util.RxSchedulers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * DataRepository
 *
 * @author zwenkai@foxmail.com ,Created on 2017-09-21 14:30:40
 *         Major Function：<b>数据存储库</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public class DataRepository {

    private static final String TAG = "DataRepository";
    static final Object TRIGGER = new Object();

    private CompositeDisposable mSubscriptions;

    public DataRepository(CompositeDisposable subscriptions) {
        this.mSubscriptions = subscriptions;
    }

    public Observable<List<PrettyGirl>> getPrettyGirlList(final int pageSize, final int page) {
//        return Observable.just(TRIGGER).compose(ensure(HttpBuilder.getRestService().getPrettyGirl(pageSize, page)));

        return Observable.create(new ObservableOnSubscribe<List<PrettyGirl>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<List<PrettyGirl>> e) throws Exception {
                Disposable disposable = HttpBuilder.getRestService().getPrettyGirl(pageSize, page)
                        .map(ResponseTransform.getInstance())
                        .compose(RxSchedulers.io2main())
                        .subscribe(
                                new Consumer<List<PrettyGirl>>() {
                                    @Override
                                    public void accept(List<PrettyGirl> prettyGirls) throws Exception {
                                        e.onNext(prettyGirls);
                                        e.onComplete();
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        e.onError(throwable);
                                    }
                                });
                mSubscriptions.add(disposable);
            }
        });

    }


}
