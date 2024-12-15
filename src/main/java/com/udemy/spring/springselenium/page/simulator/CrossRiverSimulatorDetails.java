package com.udemy.spring.springselenium.page.simulator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("web.properties")
public class CrossRiverSimulatorDetails {

    @Value("${adminconsole.app.login.url}")
    private String loginUrl;


}
