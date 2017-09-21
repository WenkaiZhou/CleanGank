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
 * PrettyGirl
 *
 * @author zwenkai@foxmail.com ,Created on 2017-09-20 23:48:50
 *         Major Function：<b>福利妹子实体</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class PrettyGirl {

    /*
     * "_id": "59c1b3e0421aa9727ddd19a8",
     * "createdAt": "2017-09-20T08:18:40.702Z",
     * "desc": "9-20",
     * "publishedAt": "2017-09-20T13:17:38.709Z",
     * "source": "chrome",
     * "type": "福利",
     * "url": "https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg",
     * "used": true,
     * "who": "带马甲"
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
