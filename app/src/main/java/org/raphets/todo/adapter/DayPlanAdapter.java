package org.raphets.todo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import org.raphets.todo.R;
import org.raphets.todo.base.BaseAdapter;
import org.raphets.todo.base.BaseViewHolder;
import org.raphets.todo.bean.DayPlanBean;

import java.util.List;

/**
 * Created by RaphetS on 2017/1/16.
 */

public class DayPlanAdapter extends SwipeMenuAdapter<BaseViewHolder> {

    private Context mContext;
    private List<DayPlanBean> mDatas;

    public DayPlanAdapter(Context mContext, List<DayPlanBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mContext).inflate(R.layout.item_plan,parent,false);
    }

    @Override
    public BaseViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new BaseViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_plan,mDatas.get(position).getPlan());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
