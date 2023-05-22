package app.service;

import app.errors.GenericException;
import app.models.Customer;
import app.util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SubscriberService {
    private Customer customer;
    public SubscriberService() {}

    public Customer startSubscription(String subscriptionDate) {
        try {
            LocalDate date = DateUtil.get(subscriptionDate);
            Customer customer = new Customer(date);
            this.customer = customer;
            return this.customer;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new GenericException("INVALID_DATE");
        }
    }

    public Customer getSubscriber() {
        return this.customer;
    }

}
