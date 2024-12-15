package com.udemy.spring.springselenium.page.simulator;

import com.udemy.spring.springselenium.entity.SimulatorStatus;
import com.udemy.spring.springselenium.web.annotation.Page;
import com.udemy.spring.springselenium.page.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

@Page
public class CrossRiverSimulatorPage extends Base {

    @FindBy(name = "userId")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(css = "table.table.table-bordered")
    private WebElement table;

    public void goTo(final String url){
        this.driver.get(url);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password + Keys.ENTER);
    }

    public List<String> getTransactionIdList(List<String> transactionPtxList) {
        var rows = this.table.findElements(By.cssSelector("tbody tr"));
        var transactionIdList = new ArrayList<String>();

        for (WebElement row : rows) {
            var cols = row.findElements(By.cssSelector("td"));
            if (transactionPtxList.contains(cols.get(2).getText())) {
                transactionIdList.add(cols.get(0).getText());
            }
        }

        return transactionIdList;
    }

    public void setStatus(SimulatorStatus event) {
        var selectElement = driver.findElement(By.id("paymentStatus"));
        var select = new Select(selectElement);

        if (SimulatorStatus.REJECTED.equals(event)) {
            select.selectByVisibleText(SimulatorStatus.REJECTED.getStatus());

            WebElement returnCodeInput = driver.findElement(By.id("statusReturnCode"));
            returnCodeInput.sendKeys("R01");
        } else {
            select.selectByVisibleText(event.getStatus());
        }

        WebElement submitBtn = driver.findElement(By.id("submit"));
        submitBtn.click();
        sleep(3000L);
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
