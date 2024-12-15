package com.udemy.spring.springselenium.page.adminconsole;

import com.udemy.spring.springselenium.web.annotation.Page;
import com.udemy.spring.springselenium.page.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Page
public class AdminConsolePage extends Base {

    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id=\"sortabletable\"]/tbody/tr[1]/td[11]/a")
    private WebElement firstFile;
    @FindBy(id = "cutoffDate")
    private WebElement cutoffField;
    @FindBy(xpath = "//*[@id=\"modalUpdateFile\"]/div/div/div[4]/button[1]")
    private WebElement modalButton;
    @FindBy(xpath = "//*[@id=\"sortabletable\"]/tbody/tr[1]/td[1]/a")
    private WebElement firstFileId;
    @FindBy(id = "sortabletable")
    private WebElement transactionsTable;
    @FindBy(xpath = "//*[@id=\"frm1\"]/table/tbody/tr[15]/td[6]/button") // TODO mudar referencia, isso muda o tempo todo
    private WebElement achProcessorButton;

    public void goTo(final String url){
        this.driver.get(url);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password + Keys.ENTER);
        sleep(5000L);
    }

    public String updateCutoff() {
        sleep(3000L);
        this.firstFile.click();

        sleep(3000L);
        Calendar cutoff = Calendar.getInstance();
        cutoff.add(Calendar.MINUTE, 1);
        cutoff.set(Calendar.SECOND, 0);
        Date newCutoff = cutoff.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(newCutoff);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormat.format(newCutoff);

        sleep(2000L);
        this.cutoffField.sendKeys(formattedDate + Keys.ARROW_RIGHT + formattedTime);
        this.modalButton.click();

        sleep(Math.abs(newCutoff.getTime() - new Date().getTime()) + 1000L);

        return firstFileId.getText();
    }

    public List<String> getTransactionIdsByFileId() {
        List<WebElement> rows = this.transactionsTable.findElements(By.xpath("//*[@id=\"sortabletable\"]/tbody/tr/td[4]"));
        return rows.stream()
                .map(WebElement::getText)
                .filter(text -> text.startsWith("ptx"))
                .toList();
    }

    public void runAchProcessor() {
        sleep(2000L);
        this.achProcessorButton.click();
        sleep(3000L);
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
