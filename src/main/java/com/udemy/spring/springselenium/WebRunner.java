package com.udemy.spring.springselenium;

import com.udemy.spring.springselenium.page.adminconsole.AdminConsoleDetails;
import com.udemy.spring.springselenium.page.adminconsole.AdminConsolePage;
import com.udemy.spring.springselenium.page.lightbox.LightboxAppDetails;
import com.udemy.spring.springselenium.page.lightbox.LightboxPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebRunner implements ApplicationRunner {

    @Autowired
    private LightboxPage lightboxPage;
    @Autowired
    private LightboxAppDetails appDetails;
    @Autowired
    private AdminConsolePage adminConsolePage;
    @Autowired
    private AdminConsoleDetails adminConsoleDetails;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        createTransaction();
//        authenticate();
//        String fileId = updateCutoff();
//        List<String> transactionIds = getFileTransactionIds(fileId);
//        runAchProcessor();
        // simulador -- status
//        runAchProcessor();
        // simulador -- status
//        runAchProcessor();
    }

    private void createTransaction() throws InterruptedException {
        this.lightboxPage.goTo(this.appDetails.getUrl());
        this.lightboxPage.selectBankList("Demo Bank");
//        this.lightboxPage.bankLogin("a", "a");
    }

    private void authenticate() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getLoginUrl());
        this.adminConsolePage.login("admin", "superadmin");
    }

    private String updateCutoff() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getAchproUrl());
        return this.adminConsolePage.updateCutoff();
    }

    private List<String> getFileTransactionIds(String fileId) {
        this.adminConsolePage.goTo(String.format(this.adminConsoleDetails.getAchproFileUrl(), fileId));
        return this.adminConsolePage.getTransactionIdsByFileId();
    }

    private void runAchProcessor() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getFigtwProcessorUrl());
        this.adminConsolePage.runAchProcessor();
    }
}
