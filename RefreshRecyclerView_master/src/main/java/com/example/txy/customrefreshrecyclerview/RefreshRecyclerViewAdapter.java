package com.example.txy.customrefreshrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txy on 2016/10/27.
 */

public class RefreshRecyclerViewAdapter extends RecyclerView.Adapter<RefreshRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> datas;

    public RefreshRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        datas = new ArrayList<>();
       for(int i = 1; i <= 20; i++) {
         datas.add("我是Content ："+i);
       }
    }

    @Override
    public RefreshRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = View.inflate(mContext, R.layout.item_recycler, null);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RefreshRecyclerViewAdapter.ViewHolder holder, final int position) {
        String data = datas.get(position);
        holder.tv_content.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_content;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
