/*
 * Copyright (c) 2018 Kevin zhou
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
package com.kevin.gank.app.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import com.kevin.gank.R
import com.kevin.mvvm.annotation.Autowired

/**
 * MainActivity
 *
 * @author zwenkai@foxmail.com, Created on 2018-12-28 19:03:51
 *         Major Function：<b>MainActivity</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class MainActivity : AppCompatActivity() {

    @Autowired
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setLightMode(this)
        initFragment()
    }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_activity_content, ContainerFragment(), ContainerFragment.TAG)
        transaction.commit()
    }
}
