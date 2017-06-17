package org.raphets.todo.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import org.raphets.todo.R;
import org.raphets.todo.adapter.DayPlanAdapter;
import org.raphets.todo.base.BaseFragment;
import org.raphets.todo.bean.DayPlanBean;
import org.raphets.todo.common.DanweiUtil;
import org.raphets.todo.common.RealmHelper;
import org.raphets.todo.common.SnackBarUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


/**
 * 日计划
 */
public class DayFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    private DayPlanAdapter mAdapter;
    private List<DayPlanBean> mDatas=new ArrayList<>();

    private SwipeMenuCreator swipeMenuCreator ;
    private boolean isFisrt=true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_day;
    }

    @Override
    protected void initEvents() {

        initData();

        initRecyclerView();

        getData();

        addListener();
    }

    private void initData() {
        swipeMenuCreator=new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {


                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                        .setBackgroundColor(Color.RED)
                        .setImage(R.drawable.ic_delete) // 图标。
                        .setWidth(DanweiUtil.dp2px(getActivity(),80))
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.

            }
        };

        srl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

    }


    private void getData() {
        List<DayPlanBean> datas= RealmHelper.queryDayPlan();
        mDatas.clear();
        mDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();

        if (srl.isRefreshing()){
            srl.setRefreshing(false);
        }

        if (mDatas.size()==0){
            tvEmpty.setVisibility(View.VISIBLE);
        }else {
            tvEmpty.setVisibility(View.GONE);
        }

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mAdapter=new DayPlanAdapter(getActivity(),mDatas);
        recyclerView.setAdapter(mAdapter);
    }


    private void addListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        recyclerView.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                RealmHelper.delDayPlan(adapterPosition);
                mDatas.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);
                if (mDatas.size()==0){
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFisrt){
            getData();
        }
        isFisrt=false;
    }
}
