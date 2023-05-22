package app.service.impl;

import app.commands.SubscriptionCommandFactory;
import app.commands.SubscriptionCommandService;
import app.commands.SubscriptionCommandsType;
import app.service.SubscriberService;
import app.service.interfaces.Provider;

public class SubscriptionProvider implements Provider {
    private SubscriptionCommandFactory subscriptionCommandFactory;
    private SubscriberService subscriberService;

    public SubscriptionProvider() {
        this.subscriberService = new SubscriberService();
        this.subscriptionCommandFactory = new SubscriptionCommandFactory(subscriberService);
    }

    @Override
    public void execute(String request) {
        String[] subscriptionRequestData = request.split("\\s");

        SubscriptionCommandsType commandsType = SubscriptionCommandsType.valueOf(subscriptionRequestData[0]);
        SubscriptionCommandService commandService = subscriptionCommandFactory.getCommandService(commandsType);
        commandService.executeCommand(subscriptionRequestData);
    }
}
