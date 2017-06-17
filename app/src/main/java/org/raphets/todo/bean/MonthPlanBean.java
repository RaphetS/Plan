package org.raphets.todo.bean;

import io.realm.RealmObject;

/**
 * Created by RaphetS on 2017/1/16.
 */

public class MonthPlanBean extends RealmObject {
    private String plan;

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
