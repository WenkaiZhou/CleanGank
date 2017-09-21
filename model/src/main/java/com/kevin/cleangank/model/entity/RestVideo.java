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
package com.kevin.cleangank.model.entity;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * RestVideo
 *
 * @author zwenkai@foxmail.com ,Created on 2017-09-21 00:10:55
 *         Major Function：<b>福利妹子实体</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class RestVideo {

    /*
     * "_id": "59be78a8421aa911847a03a8",
     * "createdAt": "2017-09-17T21:29:12.58Z",
     * "desc": "【爆首映】《猩球崛起3》最大的亮点不是凯撒，而是宋小宝！",
     * "publishedAt": "2017-09-20T13:17:38.709Z",
     * "source": "chrome",
     * "type": "休息视频",
     * "url": "http://www.bilibili.com/video/av14549261/",
     * "used": true,
     * "who": "LHF"
     */
    @JsonField(name = "_id")
    public String id = "";
    public String createdAt = "";
    public String desc = "";
    public String publishedAt = "";
    public String source = "";
    public String type = "";
    public String url = "";
    public boolean used = false;
    public String who = "";
}
