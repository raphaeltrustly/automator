package com.udemy.spring.springselenium.web.config;

import com.udemy.spring.springselenium.web.annotation.LazyConfiguration;
import com.udemy.spring.springselenium.web.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;

@Profile("!remote")
@LazyConfiguration
public class WebDriverConfig {

    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        return new ChromeDriver();
    }

}
