package app.commands;

import app.errors.GenericException;
import app.errors.NotFoundException;
import app.models.Customer;
import app.models.enums.Category;
import app.service.SubscriberService;
import app.util.DateUtil;

import java.time.LocalDate;
import java.util.Map;
import java.util.Queue;

public class RenewalService implements SubscriptionCommandService {

    private SubscriberService subscriberService;

    public RenewalService(SubscriberService service) {
        this.subscriberService = service;
    }

    @Override
    public void executeCommand(String[] commandArguments) {
        printRenewalSummary();
    }

    private void printRenewalSummary() {
        try {
            Customer customer = subscriberService.getSubscriber();
            if(customer==null) throw new NotFoundException("SUBSCRIPTIONS_NOT_FOUND");
            Map<Category, LocalDate> renewalDetail = customer.getRenewalDateForAll();
            Queue<Category> categoryOrderOfSubscription = customer.getCategoryOrderOfSubscription();
            while(!categoryOrderOfSubscription.isEmpty()) {
                Category curCategory = categoryOrderOfSubscription.poll();
                printRenewalDate(curCategory, renewalDetail.get(curCategory));
            }

            int renewalAmount = customer.getTotalRenewalAmount();
            printRenewalAmount(renewalAmount);
        } catch (GenericException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printRenewalDate(Category category, LocalDate date) {
        StringBuilder builder = new StringBuilder();
        builder.append("RENEWAL_REMINDER ");
        builder.append(category.name());
        builder.append(" ");
        builder.append(DateUtil.getDateInString(date));
        System.out.println(builder.toString());
    }

    private void printRenewalAmount(int renewalAmount) {
        System.out.println("RENEWAL_AMOUNT "+renewalAmount);
    }
}
