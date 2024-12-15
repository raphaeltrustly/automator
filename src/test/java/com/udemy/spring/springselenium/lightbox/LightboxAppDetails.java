package com.udemy.spring.springselenium.lightbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("testLightboxAppDetails")
@PropertySource("language/${app.locale:en}.properties")
public class LightboxAppDetails {

    @Value("${lightbox.app.url}")
    private String url;

    public String getUrl() {
        return url;
    }

}
