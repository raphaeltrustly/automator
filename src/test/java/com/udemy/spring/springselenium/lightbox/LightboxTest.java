package com.udemy.spring.springselenium.lightbox;

import com.udemy.spring.springselenium.SpringBaseTestNGTest;
import com.udemy.spring.springselenium.entity.PaymentInfo;
import com.udemy.spring.springselenium.entity.PaymentTypeEnum;
import com.udemy.spring.springselenium.page.lightbox.LightboxPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class LightboxTest extends SpringBaseTestNGTest {

    @Autowired
    private LightboxPage lightboxPage;

    @Autowired
    private LightboxAppDetails appDetails;

    @Test
    public void lightboxTest() throws InterruptedException {
        PaymentInfo paymentInfo = new PaymentInfo(
                PaymentTypeEnum.INSTANT,
                "2.00");

        this.lightboxPage.goTo(this.appDetails.getUrl());
        this.lightboxPage.configurePayment(paymentInfo);
        this.lightboxPage.selectBankList("Demo Bank");
        this.lightboxPage.bankLogin(paymentInfo);
    }
}
