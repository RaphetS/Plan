package org.raphets.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.raphets.todo.base.BaseActivity;
import org.raphets.todo.bean.DayPlanBean;
import org.raphets.todo.bean.MonthPlanBean;
import org.raphets.todo.bean.WeekPlanBean;
import org.raphets.todo.bean.YearPlanBean;
import org.raphets.todo.common.Consts;
import org.raphets.todo.common.RealmHelper;
import org.raphets.todo.common.SnackBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加计划
 */
public class AddPlanActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_plan)
    EditText etPlan;
    @BindView(R.id.activity_add_plan)
    LinearLayout llContainer;

    private int mFrom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_plan;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        setupToolBar();
    }

    private void initData() {
        mFrom=getIntent().getIntExtra(Consts.FROM,1);
    }

    private void setupToolBar() {
        switch (mFrom){
            case Consts.FROM_DAY:
                toolbar.setTitle("添加日计划");
                break;
            case Consts.FROM_WEEK:
                toolbar.setTitle("添加周计划");
                break;
            case Consts.FROM_MONTH:
                toolbar.setTitle("添加月计划");
                break;
            case Consts.FROM_YEAR:
                toolbar.setTitle("添加年计划");
                break;
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_plan_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                String plan=etPlan.getText().toString().trim();
                if (TextUtils.isEmpty(plan)){
                    SnackBarUtil.showShort(llContainer,"您还没有写计划呢  (^o^)");
                    break;
                }
                switch (mFrom){
                    case Consts.FROM_DAY:
                        DayPlanBean dayPlanBean=new DayPlanBean();
                        dayPlanBean.setPlan(plan);
                        RealmHelper.addDayPlan(dayPlanBean);
                        break;
                    case Consts.FROM_WEEK:
                        WeekPlanBean weekPlanBean=new WeekPlanBean();
                        weekPlanBean.setPlan(plan);
                        RealmHelper.addWeekPlan(weekPlanBean);
                        break;
                    case Consts.FROM_MONTH:
                        MonthPlanBean monthPlanBean=new MonthPlanBean();
                        monthPlanBean.setPlan(plan);
                        RealmHelper.addMonthPlan(monthPlanBean);
                        break;
                    case Consts.FROM_YEAR:
                        YearPlanBean yearPlanBean=new YearPlanBean();
                        yearPlanBean.setPlan(plan);
                        RealmHelper.addYearPlan(yearPlanBean);
                        break;
                }

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
