package org.raphets.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import org.raphets.todo.R;
import org.raphets.todo.base.BaseViewHolder;
import org.raphets.todo.bean.MonthPlanBean;
import org.raphets.todo.bean.WeekPlanBean;

import java.util.List;

/**
 * Created by RaphetS on 2017/1/16.
 */

public class MonthPlanAdapter extends SwipeMenuAdapter<BaseViewHolder> {

    private Context mContext;
    private List<MonthPlanBean> mDatas;

    public MonthPlanAdapter(Context mContext, List<MonthPlanBean> mDatas) {
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
