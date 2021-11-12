package org.selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * here we are exploring the all wrapper utility methods of webDriverRunner class its also useful
 * if you want to use traditional selenium webdriver methods
 */

public class WebDriverRunnerTest {

    Logger log = Logger.getLogger(WebDriverRunnerTest.class);

    @Test
    public  void hrmDemoTest()
    {

        // Returning the Underlying instance of WebDriver, through this we can use many webDriver native methods

        //WebDriverRunner.getWebDriver().manage().window().maximize();

        Configuration.pageLoadStrategy="eager";
        Configuration.timeout=6000;
        open("https://opensource-demo.orangehrmlive.com/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
       // WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait( Duration.ofSeconds(10));

        WebDriverRunner.clearBrowserCache();

        // we can set implicit wait throgh Webdriver Runner class


        //with this method we are getting the current page url
        log.info("Current Url is:"+WebDriverRunner.url());
        Assert.assertTrue(WebDriverRunner.url().contains("orangehrm"));
        //getting the page source
        log.info(WebDriverRunner.source());

        //we can check whether we are executing on chrome/firefox/edge so taht we can put a condition on basis

          if(WebDriverRunner.isChrome())
          {
              log.info("We are executing on chrome Browser");
          }

          List<File> files =WebDriverRunner.getBrowserDownloadsFolder().files();
          log.info(files.size());

          WebDriverRunner.closeWebDriver();


    }

}
