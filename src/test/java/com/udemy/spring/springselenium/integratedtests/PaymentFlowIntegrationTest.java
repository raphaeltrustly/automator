package com.udemy.spring.springselenium.integratedtests;

import com.udemy.spring.springselenium.SpringBaseTestNGTest;
import com.udemy.spring.springselenium.entity.PaymentInfo;
import com.udemy.spring.springselenium.entity.PaymentTypeEnum;
import com.udemy.spring.springselenium.entity.SimulatorStatus;
import com.udemy.spring.springselenium.page.adminconsole.AdminConsoleDetails;
import com.udemy.spring.springselenium.page.adminconsole.AdminConsolePage;
import com.udemy.spring.springselenium.page.lightbox.LightboxAppDetails;
import com.udemy.spring.springselenium.page.lightbox.LightboxPage;
import com.udemy.spring.springselenium.page.simulator.CrossRiverSimulatorPage;
import com.udemy.spring.springselenium.simulator.CrossRiverSimulatorDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class PaymentFlowIntegrationTest extends SpringBaseTestNGTest {

    public static final String LIGHTBOX_BANK_NAME = "Demo Bank";
    public static final PaymentInfo LIGHTBOX_PAYMENT_INFO = new PaymentInfo(PaymentTypeEnum.INSTANT, "1.00");
    public static final String ADMIN_CONSOLE_USERNAME = "admin";
    public static final String ADMIN_CONSOLE_PASSWORD = "superadmin";
    public static final String SIMULATOR_USERNAME = "admin";
    public static final String SIMULATOR_PASSWORD = "superadmin";
    @Autowired
    private LightboxPage lightboxPage;
    @Autowired
    private LightboxAppDetails appDetails;
    @Autowired
    private AdminConsolePage adminConsolePage;
    @Autowired
    private AdminConsoleDetails adminConsoleDetails;
    @Autowired
    private CrossRiverSimulatorPage crossRiverSimulatorPage;
    @Autowired
    private CrossRiverSimulatorDetails crossRiverSimulatorDetails;

    @Test
    public void run() throws Exception {
                createTransaction();
                authenticate(); // required once
                String fileId = updateCutoff();
                List<String> transactionPtxList = getFileTransactionPtxList(fileId);
                runAchProcessor();

                authenticateOnSimulator(); // required once

                simulateStatus(transactionPtxList, SimulatorStatus.SENT);
                runAchProcessor();
                simulateStatus(transactionPtxList, SimulatorStatus.REJECTED);
                runAchProcessor();
    }

    private void createTransaction() throws InterruptedException {
        this.lightboxPage.goTo(this.appDetails.getUrl());
        this.lightboxPage.selectBankList(LIGHTBOX_BANK_NAME);
        this.lightboxPage.bankLogin(LIGHTBOX_PAYMENT_INFO);
    }

    private void authenticate() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getLoginUrl());
        this.adminConsolePage.login(ADMIN_CONSOLE_USERNAME, ADMIN_CONSOLE_PASSWORD);
    }

    private String updateCutoff() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getAchproUrl());
        return this.adminConsolePage.updateCutoff();
    }

    private List<String> getFileTransactionPtxList(String fileId) {
        this.adminConsolePage.goTo(String.format(this.adminConsoleDetails.getAchproFileUrl(), fileId));
        return this.adminConsolePage.getTransactionIdsByFileId();
    }

    private void runAchProcessor() {
        this.adminConsolePage.goTo(this.adminConsoleDetails.getFigtwProcessorUrl());
        this.adminConsolePage.runAchProcessor();
    }

    private void authenticateOnSimulator() {
        crossRiverSimulatorPage.goTo(this.crossRiverSimulatorDetails.getLoginUrl());
        crossRiverSimulatorPage.login(SIMULATOR_USERNAME, SIMULATOR_PASSWORD);
    }

    private void simulateStatus(List<String> transactionIds, SimulatorStatus status) {
        crossRiverSimulatorPage.goTo(this.crossRiverSimulatorDetails.getPaymentsUrl());
        var transactionIdList = crossRiverSimulatorPage.getTransactionIdList(transactionIds);

        for (String id: transactionIdList) {
            this.crossRiverSimulatorPage.goTo(String.format(crossRiverSimulatorDetails.getTransactionUrl(), id));
            this.crossRiverSimulatorPage.setStatus(status);
        }
    }
}
