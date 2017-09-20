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
package com.kevin.cleangank.model.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * CleanGank
 *
 * @author zwenkai@foxmail.com ,Created on 2017-08-10 21:38:34
 *         Major Function：<b>项目配置</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public final class Configurator {

    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * Set the configuration is complete
     */
    public final void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * Set the Application Context
     *
     * @param context
     * @return
     */
    public final Configurator withContext(Context context) {
        CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return this;
    }

    /**
     * Set the API host
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * Set whether is the release mode
     *
     * @param released
     * @return
     */
    public final Configurator withIsReleased(boolean released) {
        CONFIGS.put(ConfigKeys.IS_RELEASED, released);
        return this;
    }

    /**
     * Set the network interceptor
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * Set the network interceptors
     *
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * Check whether the configuration is complete
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
//            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CONFIGS.get(key);
    }
}