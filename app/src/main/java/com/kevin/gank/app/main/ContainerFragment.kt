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

import android.os.Bundle
import com.kevin.gank.R
import com.kevin.mvvm.annotation.Autowired
import com.kevin.mvvm.view.BaseFragment
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * ContainerFragment
 *
 * @author zwenkai@foxmail.com, Created on 2018-11-29 13:37:49
 *         Major Function：<b>容器Fragment</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class ContainerFragment : BaseFragment() {

    companion object {
        const val TAG = "ContainerFragment"
    }

    private var loaded = false
    private lateinit var startFragment: StartFragment
    private lateinit var mainFragment: MainFragment

    @Autowired
    lateinit var binding: ContainerFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loaded = false
        initFragment()
        showMain()
    }

    private fun initFragment() {
        startFragment = StartFragment()
        mainFragment = MainFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.container_fragment_content, startFragment)
        transaction.commitAllowingStateLoss()
    }

    private fun showMain() {
        val disposable = Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe {
                    val transaction = childFragmentManager.beginTransaction()
                    transaction.replace(R.id.container_fragment_content, mainFragment)
                    transaction.commitAllowingStateLoss()
                }
        addSubscription(disposable)
    }
}