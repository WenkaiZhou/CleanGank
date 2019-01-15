/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.kevin.gank.app.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kevin.delegationadapter.AdapterDelegate;

/**
 * CompanyAdapterDelegate
 *
 * @author zhouwenkai@baidu.com, Created on 2018-12-29 22:19:17
 * Major Function：<b></b>
 * <p/>
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
public class CompanyAdapterDelegate extends AdapterDelegate<String, CompanyAdapterDelegate.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position, final String item) {
        holder.tvName.setText(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(android.R.id.text1);
        }
    }
}
