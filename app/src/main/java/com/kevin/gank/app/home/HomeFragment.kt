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
package com.kevin.gank.app.home

import android.os.Bundle
import android.view.View
import com.kevin.gank.base.GankFragment
import com.kevin.mvvm.annotation.Autowired
import com.kevin.delegationadapter.DelegationAdapter
import android.support.v7.widget.LinearLayoutManager

/**
 * HomeFragment
 *
 * @author zwenkai@foxmail.com, Created on 2018-12-29 10:12:25
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class HomeFragment : GankFragment() {

    @Autowired
    private lateinit var binding: HomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        // ② 创建 DelegationAdapter 对象
        val delegationAdapter = DelegationAdapter()
        // ③ 向Adapter中注册委托Adapter
        delegationAdapter.addDelegate(CompanyAdapterDelegate())
        // ④ 设置Adapter
        binding.recyclerView.adapter = delegationAdapter

        val companies = ArrayList<String>()
        companies.add("🇨🇳 Baidu")
        companies.add("🇨🇳 Alibaba")
        companies.add("🇨🇳 Tencent")
        companies.add("🇺🇸 Google")
        companies.add("🇺🇸 Facebook")
        companies.add("🇺🇸 Microsoft")
        companies.add("🇨🇳 Baidu")
        companies.add("🇨🇳 Alibaba")
        companies.add("🇨🇳 Tencent")
        companies.add("🇺🇸 Google")
        companies.add("🇺🇸 Facebook")
        companies.add("🇺🇸 Microsoft")
        companies.add("🇨🇳 Baidu")
        companies.add("🇨🇳 Alibaba")
        companies.add("🇨🇳 Tencent")
        companies.add("🇺🇸 Google")
        companies.add("🇺🇸 Facebook")
        companies.add("🇺🇸 Microsoft")
        companies.add("🇨🇳 Baidu")
        companies.add("🇨🇳 Alibaba")
        companies.add("🇨🇳 Tencent")
        companies.add("🇺🇸 Google")
        companies.add("🇺🇸 Facebook")
        companies.add("🇺🇸 Microsoft")
        // ⑤ 设置数据
        delegationAdapter.setDataItems(companies)
    }

}