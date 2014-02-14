package com.spring.task;

import java.util.List;
import java.util.TimerTask;

public class CheckEmailAddresses extends TimerTask {
    private List emailAddresses;

    public void setEmailAddresses(List emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void run() {
        System.out.println(emailAddresses);

    }
}
