package com.kevin.cleangank.model.net;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.ParameterizedType;
import com.kevin.cleangank.model.app.CleanGank;
import com.kevin.cleangank.model.app.ConfigKeys;
import com.kevin.cleangank.model.entity.HttpResult;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by zhouwenkai on 2017/8/10.
 */

public class ResponseTransform<T> implements Function<HttpResult<T>, T> {

    private ResponseTransform() {
    }

    static ResponseTransform getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ResponseTransform INSTANCE = new ResponseTransform();
    }

    @Override
    public T apply(@NonNull HttpResult<T> httpResult) throws Exception {
        boolean isRelease = CleanGank.getConfiguration(ConfigKeys.IS_RELEASED);
        if (!isRelease) {
            try {
                String json = new JSONObject(
                        LoganSquare.serialize(httpResult,
                                new ParameterizedType<HttpResult<T>>() {
                                })).toString(4);
                Timber.tag("Response").i(json);
            } catch (Exception e) {
                Timber.tag("ResponseTransform").w(e, "Error when serialize %s", httpResult.toString());
            }
        }
        return httpResult.data;
    }
}
