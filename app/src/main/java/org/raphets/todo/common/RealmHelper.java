package org.raphets.todo.common;

import android.content.Context;

import org.raphets.todo.bean.DayPlanBean;
import org.raphets.todo.bean.MonthPlanBean;
import org.raphets.todo.bean.WeekPlanBean;
import org.raphets.todo.bean.YearPlanBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper {

    /**
     * 添加日计划
     */
   public static void addDayPlan(DayPlanBean dayPlanBean){
       Realm mRealm=Realm.getDefaultInstance();

       mRealm.beginTransaction();
       mRealm.copyToRealm(dayPlanBean);
       mRealm.commitTransaction();
   }

    /**
     * 删除日计划
     */
    public static  void delDayPlan(int position){
        Realm mRealm=Realm.getDefaultInstance();
        mRealm.beginTransaction();

        RealmResults realmResults=mRealm.where(DayPlanBean.class).findAll();
        realmResults.deleteFromRealm(position);

        mRealm.commitTransaction();
    }

    /**
     * 查询日计划
     */
    public static  List<DayPlanBean> queryDayPlan(){
        Realm mRealm=Realm.getDefaultInstance();

        RealmResults<DayPlanBean> list=mRealm.where(DayPlanBean.class).findAll();
        return mRealm.copyFromRealm(list);
    }

    /**
     * 添加周计划
     */
    public static void addWeekPlan(WeekPlanBean weekPlanBean){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();
        mRealm.copyToRealm(weekPlanBean);
        mRealm.commitTransaction();
    }

    /**
     * 删除周计划
     */
    public static  void delWeekPlan(int position){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();

        RealmResults realmResults=mRealm.where(WeekPlanBean.class).findAll();
        realmResults.deleteFromRealm(position);

        mRealm.commitTransaction();
    }

    /**
     * 查询周计划
     */
    public static  List<WeekPlanBean> queryWeekPlan(){
        Realm mRealm=Realm.getDefaultInstance();

        RealmResults<WeekPlanBean> list=mRealm.where(WeekPlanBean.class).findAll();
        return mRealm.copyFromRealm(list);
    }


    /**
     * 添加月计划
     */
    public static  void addMonthPlan(MonthPlanBean monthPlanBean){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();
        mRealm.copyToRealm(monthPlanBean);
        mRealm.commitTransaction();
    }

    /**
     * 删除月计划
     */
    public static  void delMonthPlan(int  position){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();
        RealmResults realmResults=mRealm.where(MonthPlanBean.class).findAll();
        realmResults.deleteFromRealm(position);
        mRealm.commitTransaction();
    }

    /**
     * 查询月计划
     */
    public static  List<MonthPlanBean> queryMonthPlan(){
        Realm mRealm=Realm.getDefaultInstance();

        RealmResults<MonthPlanBean> list=mRealm.where(MonthPlanBean.class).findAll();
        return mRealm.copyFromRealm(list);
    }

    /**
     * 添加年计划
     */
    public static  void addYearPlan(YearPlanBean yearPlanBean){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();
        mRealm.copyToRealm(yearPlanBean);
        mRealm.commitTransaction();
    }

    /**
     * 删除年计划
     */
    public static  void delYearPlan(int  position){
        Realm mRealm=Realm.getDefaultInstance();

        mRealm.beginTransaction();
        RealmResults realmResults=mRealm.where(YearPlanBean.class).findAll();
        realmResults.deleteFromRealm(position);
        mRealm.commitTransaction();
    }

    /**
     * 查询年计划
     */
    public static  List<YearPlanBean> queryYearPlan(){
        Realm mRealm=Realm.getDefaultInstance();

        RealmResults<YearPlanBean> list=mRealm.where(YearPlanBean.class).findAll();
        return mRealm.copyFromRealm(list);
    }

}
