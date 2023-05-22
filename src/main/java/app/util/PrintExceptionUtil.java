package app.util;

import app.commands.SubscriptionCommandsType;
import app.errors.GenericException;

public class PrintExceptionUtil {

    public static void printException(SubscriptionCommandsType commandsType, GenericException ex) {
        if(commandsType.equals(SubscriptionCommandsType.PRINT_RENEWAL_DETAILS)) System.out.println(ex.getMessage());
        else System.out.println(commandsType.name()+"_FAILED "+ex.getMessage());
    }
}
