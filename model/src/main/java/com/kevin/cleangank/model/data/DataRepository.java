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
import com.kevin.cleangank.model.entity.RestVideo;
import com.kevin.cleangank.model.net.HttpHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

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

    private CompositeDisposable mSubscriptions;

    public DataRepository(CompositeDisposable subscriptions) {
        this.mSubscriptions = subscriptions;
    }

    /**
     * 获取福利数据列表
     *
     * @param pageSize
     * @param page
     * @return
     */
    public Observable<List<PrettyGirl>> getPrettyGirlList(final int pageSize, final int page) {
        return HttpHelper.request(mSubscriptions, HttpHelper.api().getPrettyGirl(pageSize, page));
    }

    /**
     * 获取休息视频数据列表
     *
     * @param pageSize
     * @param page
     * @return
     */
    public Observable<List<RestVideo>> getRestVideoList(final int pageSize, final int page) {
        return HttpHelper.request(mSubscriptions, HttpHelper.api().getRestVideo(pageSize, page));
    }
}
