package app.commands;

import app.service.SubscriberService;


public class SubscriptionCommandFactory {

    private TopUpService topUpService;
    private RenewalService renewalService;
    private SubscriptionService subscriptionService;
    private SubscriberService subscriberService;

    public SubscriptionCommandFactory(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
        this.topUpService = new TopUpService(subscriberService);
        this.subscriptionService = new SubscriptionService(subscriberService);
        this.renewalService = new RenewalService(subscriberService);
    }

    public SubscriptionCommandService getCommandService(SubscriptionCommandsType commandsType) {
        switch (commandsType) {
            case START_SUBSCRIPTION:
            case ADD_SUBSCRIPTION: return subscriptionService;
            case ADD_TOPUP: return topUpService;
            case PRINT_RENEWAL_DETAILS: return renewalService;
        }
        throw new RuntimeException("Illegal command argument");
    }
}
