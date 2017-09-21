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

import com.kevin.cleangank.model.entity.HttpResult;
import com.kevin.cleangank.model.entity.PrettyGirl;
import com.kevin.cleangank.model.entity.RestVideo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * APIService
 *
 * @author zwenkai@foxmail.com ,Created on 2017-09-21 00:05:51
 *         Major Function：<b>Web API</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public interface APIService {

    // http://gank.io/api/data/福利/10/1
    @GET("/api/data/福利/{pageSize}/{page}")
    Observable<HttpResult<PrettyGirl>> getPrettyGirl(
            @Path("pageSize") int pageSize,
            @Path("page") int page);

    // http://gank.io/api/data/休息视频/10/1
    @GET("/api/data/休息视频/{pageSize}/{page}")
    Observable<HttpResult<RestVideo>> getRestVideo(
            @Path("pageSize") int pageSize,
            @Path("page") int page);

}
