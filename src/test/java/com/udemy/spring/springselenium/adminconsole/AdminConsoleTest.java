package com.udemy.spring.springselenium.adminconsole;

import com.udemy.spring.springselenium.SpringBaseTestNGTest;
import com.udemy.spring.springselenium.page.adminconsole.AdminConsolePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class AdminConsoleTest extends SpringBaseTestNGTest {

    @Autowired
    private AdminConsolePage adminConsolePage;

    @Autowired
    private AdminConsoleDetails adminConsoleDetails;

    @Test
    public void adminConsoleTest(){
        this.adminConsolePage.goTo(this.adminConsoleDetails.getLoginUrl());
        this.adminConsolePage.login("admin", "superadmin");
        this.adminConsolePage.goTo(this.adminConsoleDetails.getAchproUrl());
        String fileId = this.adminConsolePage.updateCutoff();

        this.adminConsolePage.goTo(String.format(this.adminConsoleDetails.getAchproFileUrl(), 1));
        List<String> transactionIds = this.adminConsolePage.getTransactionIdsByFileId();

        this.adminConsolePage.goTo(this.adminConsoleDetails.getFigtwProcessorUrl());
        this.adminConsolePage.runAchProcessor();
    }
}
