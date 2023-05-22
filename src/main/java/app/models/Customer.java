package app.models;

import app.errors.DuplicateException;
import app.errors.NotFoundException;
import app.models.enums.Category;
import app.models.enums.Plan;
import app.models.enums.TopUpPlan;

import java.time.LocalDate;
import java.util.*;

public class Customer {
    private int totalAmount;
    private LocalDate subscriptionDate;
    private Map<Category, SubscriptionDetail> subscriptionMap;
    private TopUpDetail topUpDetail;
    private Queue<Category> categoryOrderOfSubscription;

    public Customer(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        this.subscriptionMap = new HashMap<>();
        this.categoryOrderOfSubscription = new LinkedList<>();
    }

    public SubscriptionDetail addSubscription(Category category, Plan plan) {
        if(subscriptionMap.containsKey(category)) {
            throw new DuplicateException("CATEGORY");
        }

        SubscriptionDetail subscriptionDetail = new SubscriptionDetail(category, plan, subscriptionDate);
        subscriptionMap.put(category, subscriptionDetail);
        totalAmount+= subscriptionDetail.getSubscriptionCharge();
        categoryOrderOfSubscription.add(category);
        return subscriptionDetail;
    }

    public TopUpDetail addTopUp(TopUpPlan topUpPlan, int months) {
        if(subscriptionMap.isEmpty()) throw new NotFoundException("SUBSCRIPTIONS_NOT_FOUND");
        if(topUpDetail!=null) throw new DuplicateException("TOPUP");
        TopUpDetail topUpDetail = new TopUpDetail(topUpPlan, months);
        this.topUpDetail = topUpDetail;
        this.totalAmount+=topUpDetail.getTotalCharge();
        return topUpDetail;
    }

    public Map<Category, LocalDate> getRenewalDateForAll() {
        if(subscriptionMap.isEmpty()) throw new NotFoundException("SUBSCRIPTIONS_NOT_FOUND");
        Map<Category, LocalDate> renewalDateMap = new HashMap<>();
        for(Map.Entry<Category, SubscriptionDetail> entry: subscriptionMap.entrySet()) {
            renewalDateMap.put(entry.getKey(), entry.getValue().getRenewalDate());
        }
        return renewalDateMap;
    }

    public Queue<Category> getCategoryOrderOfSubscription() {
        return this.categoryOrderOfSubscription;
    }

    public int getTotalRenewalAmount() {
        return this.totalAmount;
    }
}
