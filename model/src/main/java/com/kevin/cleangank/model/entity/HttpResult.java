package com.kevin.cleangank.model.entity;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by zhouwenkai on 2017/8/10.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class HttpResult<T> {
    public boolean error;
    @JsonField(name = "results")
    public T data;
}
