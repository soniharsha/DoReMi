package app.models;

import app.errors.GenericException;
import app.models.enums.Category;
import app.models.enums.Plan;
import app.models.plans.MusicSubscriptionPlan;
import app.models.plans.PodcastSubscriptionPlan;
import app.models.plans.SubscriptionPlan;
import app.models.plans.VideoSubscriptionPlan;
import app.util.DateUtil;

import java.time.LocalDate;

public class SubscriptionDetail {
    private LocalDate subscriptionDate;
    private LocalDate renewalDate;
    private SubscriptionPlan subscriptionPlan;

    public SubscriptionDetail(Category category, Plan plan, LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        this.subscriptionPlan = getSubscriptionPlan(category, plan);
        setRenewalDate();
    }

    public LocalDate getRenewalDate() {
        return this.renewalDate;
    }

    public int getSubscriptionCharge() {
        return this.subscriptionPlan.getCharge();
    }

    public int getMonthsValidFor() {
        return  this.subscriptionPlan.getValidityInMonths();
    }
    private void setRenewalDate() {
        LocalDate renewal = DateUtil.addMonths(subscriptionDate, subscriptionPlan.getValidityInMonths());
        renewal = DateUtil.subtractDays(renewal, 10);
        this.renewalDate = renewal;
    }

    private SubscriptionPlan getSubscriptionPlan(Category category, Plan plan) {
        switch (category) {
            case MUSIC:
                return new MusicSubscriptionPlan(plan);
            case PODCAST:
                return new PodcastSubscriptionPlan(plan);
            case VIDEO:
                return new VideoSubscriptionPlan(plan);
        }
        throw new GenericException("Unhandled category passed");
    }
}
