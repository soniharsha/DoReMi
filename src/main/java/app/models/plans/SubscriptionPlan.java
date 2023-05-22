package app.models.plans;

import app.models.enums.Category;

public abstract class SubscriptionPlan {
    private Category category;

    protected SubscriptionPlan(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    public abstract int getCharge();
    public abstract int getValidityInMonths();
}
