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
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.kevin.gank.R
import com.kevin.gank.app.beauty.BeautyFragment
import com.kevin.gank.app.categoty.CategoryFragment
import com.kevin.gank.app.favorite.FavoriteFragment
import com.kevin.gank.app.home.HomeFragment
import com.kevin.gank.base.GankFragment
import com.kevin.mvvm.annotation.Autowired

/**
 * MainFragment
 *
 * @author zwenkai@foxmail.com, Created on 2018-12-28 19:10:33
 *         Major Function：<b>MainFragment</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class MainFragment : GankFragment() {

    @Autowired
    private lateinit var binding: MainFragmentBinding

    private var fragments: MutableList<Fragment> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        fragments.add(HomeFragment())
        fragments.add(CategoryFragment())
        fragments.add(BeautyFragment())
        fragments.add(FavoriteFragment())

        val selectedListener = TabSelectedListener(childFragmentManager, fragments)
        binding.tabLayout.addOnTabSelectedListener(selectedListener)
        val tab = binding.tabLayout.getTabAt(0)
        if (tab != null) {
            selectedListener.onTabSelected(tab)
        }
    }

    internal inner class TabSelectedListener(
            private val fragmentManager: FragmentManager,
            private val fragments: MutableList<Fragment>
    ) : TabLayout.OnTabSelectedListener {

        private var currentFragment: Fragment? = null

        override fun onTabSelected(tab: TabLayout.Tab) {
            if (fragments.size != 0) {
                val position = tab.position
                val transaction = fragmentManager.beginTransaction()
                if (currentFragment != null) {
                    transaction.hide(currentFragment!!)
                    currentFragment!!.userVisibleHint = false
                }

                val tag = fragments[position].javaClass.name
                var fragment = fragmentManager.findFragmentByTag(tag)
                if (fragment == null) {
                    fragment = fragments[position]
                }

                currentFragment = fragment
                if (fragment.isAdded) {
                    transaction.show(fragment)
                } else {
                    transaction.add(R.id.main_fragment_content, fragment, tag)
                }

                transaction.commitAllowingStateLoss()
                if (fragment.view == null) {
                    return
                }

                currentFragment!!.userVisibleHint = true
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}

        override fun onTabReselected(tab: TabLayout.Tab) {}
    }
}