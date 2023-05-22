package app.commands;

import app.errors.GenericException;
import app.models.Customer;
import app.models.SubscriptionDetail;
import app.models.enums.Category;
import app.models.enums.Plan;
import app.service.SubscriberService;
import app.util.PrintExceptionUtil;

public class SubscriptionService implements SubscriptionCommandService {
    private SubscriberService subscriberService;

    public SubscriptionService(SubscriberService service) {
        this.subscriberService = service;
    }

    @Override
    public void executeCommand(String[] commandArguments) {
        SubscriptionCommandsType subscriptionCommandsType = SubscriptionCommandsType.valueOf(commandArguments[0]);

        if(subscriptionCommandsType.equals(SubscriptionCommandsType.START_SUBSCRIPTION)) {
            startSubscription(commandArguments[1]);
        } else {
            Category category = Category.valueOf(commandArguments[1]);
            Plan plan = Plan.valueOf(commandArguments[2]);
            addSubscription(category, plan);
        }
    }

    private Customer startSubscription(String date) {
        try {
            return subscriberService.startSubscription(date);
        } catch (GenericException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private SubscriptionDetail addSubscription(Category category, Plan plan) {
        try {
            Customer customer = subscriberService.getSubscriber();
            if(customer==null) throw new GenericException("INVALID_DATE");
            return customer.addSubscription(category, plan);
        } catch (GenericException ex) {
            PrintExceptionUtil.printException(SubscriptionCommandsType.ADD_SUBSCRIPTION, ex);
            return null;
        }
    }
}
