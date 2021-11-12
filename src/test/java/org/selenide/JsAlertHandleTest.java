package org.selenide;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Multiple Test to handle JS Alert, fetching teh text from Alert, clicking on confirm or cancel on alert also se
 * sendkeys into Alert
 */

public class JsAlertHandleTest {


    Logger log = Logger.getLogger(JsAlertHandleTest.class);

    @BeforeClass
    public void setup() throws InterruptedException {
        log.info("Redirecting to URL");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        open("https://the-internet.herokuapp.com/javascript_alerts");
        log.info("Website open");
        Thread.sleep(2000);
    }


    @Test
    public void VerifyAlertClick() throws InterruptedException {
        log.info("Test Case Started");
        $(By.xpath("//button[text()='Click for JS Alert']")).click();
        log.info("Button clicked");

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = switchTo().alert();

        //Thread.sleep(2000);
        Assert.assertTrue(alert.getText().contains("JS Alert"));
        alert.accept();
        log.info("click on ok button on alert");
        $(By.xpath("//p[text()='You successfully clicked an alert']")).shouldHave(Condition.text("You successfully clicked an alert"));


    }

    @Test
    public void VerifyJsCancel() throws InterruptedException {
        log.info("Test Case Started");
        $(By.xpath("//button[text()='Click for JS Confirm']")).click();
        log.info("Button clicked");

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = switchTo().alert();

        //Thread.sleep(2000);
        Assert.assertTrue(alert.getText().contains("JS Confirm"));
        log.info("Alert Text is"+alert.getText());
        alert.dismiss();
        log.info("click on cancel button on alert");
        $(By.xpath("//p[text()='You clicked: Cancel']")).shouldHave(Condition.text("You clicked: Cancel"));


    }

    @Test
    public void VerifyTextInsertionInJSAlert() throws InterruptedException {
        log.info("Test Case Started");
        $(By.xpath("//button[text()='Click for JS Prompt']")).click();
        log.info("Button clicked");

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = switchTo().alert();

        //Thread.sleep(2000);
        Assert.assertTrue(alert.getText().contains("JS prompt"));
        log.info("Alert Text is"+alert.getText());
        String textTobeInserted="Welcome to Earth";
        alert.sendKeys(textTobeInserted);
        alert.accept();
        Thread.sleep(2000);
        log.info("click on accept button on alert after providing text");
        $(By.xpath("//h4[text()='Result:']//following-sibling::p")).shouldHave(Condition.text(textTobeInserted));


    }


}
