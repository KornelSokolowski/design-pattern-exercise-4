package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Observer {
    private String fullName;
    protected List<String> notifications = new ArrayList<>();

    public Customer(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void update() {
        notifications.add(LocalDateTime.now() + " " + "You have been overbided");
    }
    public void printNotifications() {
        System.out.println(notifications);
    }
}
