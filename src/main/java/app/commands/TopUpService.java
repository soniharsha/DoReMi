package app.commands;

import app.errors.GenericException;
import app.errors.NotFoundException;
import app.models.Customer;
import app.models.TopUpDetail;
import app.models.enums.TopUpPlan;
import app.service.SubscriberService;
import app.util.PrintExceptionUtil;

public class TopUpService implements SubscriptionCommandService {

    private SubscriberService subscriberService;

    public TopUpService(SubscriberService service) {
        this.subscriberService = service;
    }
    @Override
    public void executeCommand(String[] commandArguments) {
        TopUpPlan topUpPlan = TopUpPlan.valueOf(commandArguments[1]);
        addTopUp(topUpPlan, Integer.valueOf(commandArguments[2]));
    }

    private TopUpDetail addTopUp(TopUpPlan topUpPlan, int months) {
        try {
            Customer customer = subscriberService.getSubscriber();
            if(customer==null) throw new GenericException("INVALID_DATE");
            return customer.addTopUp(topUpPlan, months);
        } catch (GenericException ex) {
            PrintExceptionUtil.printException(SubscriptionCommandsType.ADD_TOPUP, ex);
            return null;
        }
    }
}
