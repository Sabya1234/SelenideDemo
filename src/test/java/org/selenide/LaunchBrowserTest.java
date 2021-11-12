package org.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class LaunchBrowserTest {

    Logger log = Logger.getLogger(LaunchBrowserTest.class);

    /**
     * You can setup your browser by two way
     * 1) Configuration.browser="edge/chrome/safari/ie/firefox" OR
     * 2)System.setProperty("selenide.browser","edge");
     * 3) For edge Driver do add WebdriverManager().edge().setup()
     */
   // @Test(dataProvider = "BrowserInfo")
    @Test
    public void googleSearchTestWithDifferentBrowser()

    {
        //PropertyConfigurator.configure("log4j.properties");
        /*if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
        }*/


        WebDriverManager.edgedriver().setup();
        //System.setProperty("selenide.browser","chrome");
        //Maven implementation of above line mvn clean install -Dselenide.browser="browser"
        Configuration.browser="edge";
        Configuration.baseUrl="https://www.google.com";
       // Configuration.browserSize
        //

        //setting up the browser binary by 2 way
        //Configuration.browserBinary="C:\\Users\\UX500019\\Downloads\\geckodriver-v0.30.0-win64";
        //System.setProperty("selenide.browserBinary","C:\\Users\\UX500019\\Downloads\\geckodriver-v0.30.0-win64");
       // Configuration.headless=true;

        open("/");

        $(By.name("q")).setValue("Naveen AutomationLabs").pressEnter();
        $(By.id("logo")).shouldHave(appear);

        //Selenide implementation of assertion of test extracted from Xpath
        $(By.xpath("//h3[text()='Welcome to Naveen AutomationLabs - naveen automationlabs']")).shouldHave(Condition.text("Welcome to Naveen AutomationLabs - naveen automationlabs"));

        //Below mentioned code snipped that was traditional testNG assertion approach

        /*String header=  $(By.xpath("//h3[text()='Welcome to Naveen AutomationLabs - naveen automationlabs']")).getText();
        System.out.println(header);
      Assert.assertEquals(header,"Welcome to Naveen AutomationLabs - naveen automationlabs");*/

        //we can check size of elements and can assert with selenide collectionElement class
        $$(By.cssSelector(".LC20lb.DKV0Md")).shouldHave(CollectionCondition.size(9));

        //else we can went for traditional approach of testNG Assertion

        int size = $$(By.cssSelector(".LC20lb.DKV0Md")).size();
        System.out.println(size);
        //Assert.assertEquals(size);


    }

    /*@DataProvider(name="BrowserInfo")
    public  Object[][] getBrowser()
    {
        return new Object[][] {
                {"edge"},
                {"chrome"},

        };
    }*/
}
