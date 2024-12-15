package com.udemy.spring.springselenium.simulator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("testCrossRiverSimulatorDetails")
@PropertySource("language/${app.locale:en}.properties")
public class CrossRiverSimulatorDetails {

    @Value("${simulator.crb.ach.app.login.url}")
    private String loginUrl;
    @Value("${simulator.crb.ach.app.transaction.url}")
    private String transactionUrl;
    @Value("${simulator.crb.ach.app.payments.url}")
    private String paymentsUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public String getPaymentsUrl() {
        return paymentsUrl;
    }
}
