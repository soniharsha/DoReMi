package app.models;

import app.models.enums.TopUpPlan;

public class TopUpDetail {
    private TopUpPlan topUpPlan;
    private int validityInMonths;

    public TopUpDetail(TopUpPlan plan, int months) {
        this.topUpPlan = plan;
        this.validityInMonths = months;
    }

    public int getTotalCharge() {
        return this.validityInMonths * this.topUpPlan.getMonthlyCharge();
    }
}
