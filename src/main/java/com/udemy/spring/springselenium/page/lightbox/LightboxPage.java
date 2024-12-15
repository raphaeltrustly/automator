package com.udemy.spring.springselenium.page.lightbox;

import com.udemy.spring.springselenium.entity.LightBoxCredentials;
import com.udemy.spring.springselenium.entity.PaymentInfo;
import com.udemy.spring.springselenium.web.annotation.Page;
import com.udemy.spring.springselenium.page.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@Page
public class LightboxPage extends Base {

    @FindBy(id = "paywithmybank-iframe-widget-container")
    private WebElement bankList;
    @FindBy(id = "wdt-bankTiles-openAllBanks")
    private WebElement bankListButton;
    @FindBy(id = "wdt-bankTiles-openBank200005501")
    private WebElement demoBankButton;
    @FindBy(id = "paywithmybank-iframe")
    private WebElement bankListFrame;
    @FindBy(id = "lbx-listBank-inputSearch")
    private WebElement bankNameInput;
    @FindBy(id = "paywithmybank-iframe")
    private WebElement bankDataFrame;
    @FindBy(id = "slider-button")
    private WebElement popup;
    @FindBy(id = "lbx-iframeAuthenticate")
    private WebElement loginFrame;
    @FindBy(id = "paywithmybank-iframe")
    private WebElement accountFrame;
    @FindBy(id = "paymentType")
    private WebElement paymentType;
    @FindBy(id = "amountInput")
    private WebElement amountInput;

    public void goTo(final String url){
        this.driver.get(url);
    }

    public void configurePayment(PaymentInfo paymentInfo) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentType")));
        Select paymentTypeSelect = new Select(this.paymentType);
        paymentTypeSelect.selectByVisibleText(paymentInfo.getPaymentType().getValue());
        sleep(2000L);

        // problema no código para setar o amount, a listagem de FIs para de funcionar
//        this.amountInput.clear();
//        this.amountInput.sendKeys(paymentInfo.getAmount());
        // configurar canadá no futuro
    }

    public void selectBankList(String bankName) {
        this.driver.switchTo().frame(this.bankList);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wdt-bankTiles-openAllBanks")));

        this.bankListButton.click();
        this.driver.switchTo().defaultContent();
        this.driver.switchTo().frame(this.bankListFrame);
        this.bankNameInput.sendKeys(bankName + Keys.ENTER);
        sleep(3000L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("bank-list-inner-container")));
        WebElement selectedBankDiv = this.driver.findElement(
                By.xpath("//*[@id=\"bank-list-inner-container\"]/div[1]"));
        selectedBankDiv.sendKeys(Keys.ENTER);
        this.driver.switchTo().defaultContent();
    }

    public void bankLogin(PaymentInfo paymentInfo) {
        LightBoxCredentials credentials = paymentInfo.getLightBoxCredentials();
        driver.switchTo().frame(this.bankDataFrame);
        sleep(4000L);
//        this.popup.click();
        driver.switchTo().frame(this.loginFrame);
        driver.findElement(By.id("lbx-formAuthenticate-authFields-inputusername")).sendKeys(credentials.getUsername());
        driver.findElement(By.id("lbx-formAuthenticate-authFields-inputpassword")).sendKeys(credentials.getPassword() + Keys.ENTER);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(this.accountFrame);
        driver.findElement(By.id("lbx-accountList-submit")).click();
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
