package org.selenide;

import com.codeborne.selenide.Configuration;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Here we are exploring the selenide window handling wrapper methods powered by selenium webDriver
 * we also exploring here pageload strategy wrapper method
 * and below Test performs selenide closeWindow() and closeWebdriver() which is driver.close() and driver.quit()
 * respectively
 *
 */
public class WindowHandlingConcept {

    Logger log = Logger.getLogger(WindowHandlingConcept.class);

    @Test
    public void windowHandleTest() throws InterruptedException {
        log.info("Test started");
        Configuration.browserSize="1920x1080";
        //Configuration.pageLoadStrategy="eager";
        System.getProperty("selenide.pageLoadStrategy","eager");
        open("https://opensource-demo.orangehrmlive.com/");//---Parent window
        log.info("web page title is  "+title());

        //clicking on youtube icon to witch into another window
        $("img[alt='OrangeHRM on youtube']").click();//----child window

        //switch to new youtube window after click to pass windowHandle to driver
        switchTo().window(1);
        log.info("we are on child window "+title());
        Assert.assertTrue(title().contains("YouTube"));
        Thread.sleep(3000);

        //closing the current browser window its same sa webdriver.close ()

        closeWindow();
        switchTo().window(0);
        log.info("we are on parent window again"+title());
        Assert.assertTrue(title().contains("Orange"));
        Thread.sleep(3000);

        // ending the webdriver session it will close current as well as every associated window of browser
        closeWebDriver();

    }
}
