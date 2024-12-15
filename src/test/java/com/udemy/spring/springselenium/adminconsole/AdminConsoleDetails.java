package com.udemy.spring.springselenium.adminconsole;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("testAdminConsoleDetails")
@PropertySource("language/${app.locale:en}.properties")
public class AdminConsoleDetails {

    @Value("${adminconsole.app.login.url}")
    private String loginUrl;
    @Value("${adminconsole.app.services.achpro.url}")
    private String achproUrl;
    @Value("${adminconsole.app.services.achpro.file.url}")
    private String achproFileUrl;
    @Value("${adminconsole.app.services.figatewayprocessor.url}")
    private String figtwProcessorUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getAchproUrl() {
        return achproUrl;
    }

    public String getAchproFileUrl() {
        return achproFileUrl;
    }

    public String getFigtwProcessorUrl() {
        return figtwProcessorUrl;
    }
}
