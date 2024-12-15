package com.udemy.spring.springselenium.simulator;

import com.udemy.spring.springselenium.SpringBaseTestNGTest;
import com.udemy.spring.springselenium.entity.SimulatorStatus;
import com.udemy.spring.springselenium.page.simulator.CrossRiverSimulatorPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class CrossRiverSimulatorTest extends SpringBaseTestNGTest {

    public static final String SIMULATOR_USERNAME = "admin";
    public static final String SIMULATOR_PASSWORD = "superadmin";

    @Autowired
    CrossRiverSimulatorPage crossRiverSimulatorPage;
    @Autowired
    CrossRiverSimulatorDetails crossRiverSimulatorDetails;

    @Test
    public void simulatorTest(){
        var status = SimulatorStatus.REJECTED;
        var ptxList = List.of("ptx-Bo-HxkuXGxxI1JoUv3hMzpnf-rap");

        this.crossRiverSimulatorPage.goTo(this.crossRiverSimulatorDetails.getLoginUrl());
        this.crossRiverSimulatorPage.login(SIMULATOR_USERNAME, SIMULATOR_PASSWORD);

        List<String> transactionIdList = this.crossRiverSimulatorPage.getTransactionIdList(ptxList);

        for (String id: transactionIdList) {
            this.crossRiverSimulatorPage.goTo(String.format(crossRiverSimulatorDetails.getTransactionUrl(), id));
            this.crossRiverSimulatorPage.setStatus(status);
        }
    }


}
