package com.kevin.cleangank.model.net;

import android.support.annotation.NonNull;

import com.kevin.cleangank.model.entity.HttpResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by zhouwenkai on 2017/8/10.
 */

public class HttpHelper {

    public static
    @NonNull
    <T> Disposable request(
            @NonNull final CompositeDisposable subscriptions,
            @NonNull final Observable<HttpResult<T>> observable,
            @NonNull final Consumer<T> success,
            @NonNull final Consumer<Throwable> error) {

        Disposable disposable = observable
                .map(ResponseTransform.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, new ApiException<>(error)
                        , new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
        subscriptions.add(disposable);
        return disposable;
    }


}
