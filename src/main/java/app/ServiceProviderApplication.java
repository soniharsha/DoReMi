package app;

import app.service.impl.FileInterface;
import app.service.interfaces.UserInterface;

public class ServiceProviderApplication {

    public static void main(String[] args) {
        UserInterface userInterface = new FileInterface();
        userInterface.interact(args);
    }
}
