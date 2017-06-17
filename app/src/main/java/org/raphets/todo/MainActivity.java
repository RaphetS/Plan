package org.raphets.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import org.raphets.todo.base.BaseActivity;
import org.raphets.todo.common.Consts;
import org.raphets.todo.common.SnackBarUtil;
import org.raphets.todo.fragment.DayFragment;
import org.raphets.todo.fragment.MonthFragment;
import org.raphets.todo.fragment.WeekFragment;
import org.raphets.todo.fragment.YearFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawer;
    @BindView(R.id.fl_main)
    FrameLayout mFLMain;

    private ActionBarDrawerToggle mDrawerToggle;

    private DayFragment mDayFragment;
    private MonthFragment mMonthFragment;
    private WeekFragment mWeekFragment;
    private YearFragment mYearFragment;
    private Fragment mCurrentFragmet;

    private MenuItem mCurrentItem;

    private String[] mTitles=new String[]{"日计划","周计划","月计划","年计划"};

    private int mCurrent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        initData();

        addListener();
    }




    private void initUI() {
        mToolBar.setTitle(mTitles[0]);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawer.addDrawerListener(mDrawerToggle);

    }

    private void initData() {
        mDayFragment=new DayFragment();
        mWeekFragment=new WeekFragment();
        mMonthFragment=new MonthFragment();
        mYearFragment=new YearFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_main, mDayFragment)
                .commit();
        mCurrentFragmet = mDayFragment;
        mCurrentItem = mNavigation.getMenu().findItem(R.id.drawer_day);
        mCurrentItem.setChecked(true);
        mCurrent=Consts.FROM_DAY;


        SnackBarUtil.showShort(mFLMain,"侧滑可以删除计划哟 (^o^)");
    }

    @Override
    public void onBackPressed() {
        if (mNavigation.isShown()) {
            mDrawer.closeDrawers();
        } else {
            showExit();
        }
    }

    private void showExit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("您确定要退出吗？")
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAll();
                        System.exit(0);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .setCanceledOnTouchOutside(false);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent intent=new Intent(MainActivity.this,AddPlanActivity.class);
                intent.putExtra(Consts.FROM,mCurrent);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment to) {
        if (mCurrentFragmet != null) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (to.isAdded()) {
                transaction.hide(mCurrentFragmet).show(to).commit();
            } else {
                transaction.hide(mCurrentFragmet).add(R.id.fl_main, to).commit();
            }

        }

    }

    private void addListener() {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawer.closeDrawers();
                mCurrentItem.setChecked(false);
                mCurrentItem = item;
                switch (item.getItemId()) {
                    case R.id.drawer_day:
                        switchFragment(mDayFragment);
                        mCurrentFragmet = mDayFragment;
                        mToolBar.setTitle(mTitles[0]);
                        mCurrent=Consts.FROM_DAY;
                        break;
                    case R.id.drawer_week:
                        switchFragment(mWeekFragment);
                        mCurrentFragmet = mWeekFragment;
                        mToolBar.setTitle(mTitles[1]);
                        mCurrent=Consts.FROM_WEEK;
                        break;
                    case R.id.drawer_month:
                        switchFragment(mMonthFragment);
                        mCurrentFragmet = mMonthFragment;
                        mToolBar.setTitle(mTitles[2]);
                        mCurrent=Consts.FROM_MONTH;
                        break;
                    case R.id.drawer_year:
                        switchFragment(mYearFragment);
                        mCurrentFragmet = mYearFragment;
                        mToolBar.setTitle(mTitles[3]);
                        mCurrent=Consts.FROM_YEAR;
                        break;
                }

                return true;
            }
        });
    }
}
