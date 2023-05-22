package app.service.impl;

import app.service.interfaces.Provider;
import app.service.interfaces.UserInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInterface implements UserInterface {

    private Provider provider;

    public FileInterface() {
        this.provider = new SubscriptionProvider();
    }

    @Override
    public void interact(String[] args) {
        String filePath = args[0];
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            while(line!=null) {
                provider.execute(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
