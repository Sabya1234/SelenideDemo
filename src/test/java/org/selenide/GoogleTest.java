package org.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.apache.log4j.*;

/**
 * put log4j.properties in src/main/resource folder that is in build maven path hence its automatically add
 * the file in classPath  if you have not put that into that folder Then you need to use
 * PropertyConfigurator.configure("logfile name") in each Test Method
 */
public class GoogleTest {


    Logger log = Logger.getLogger(GoogleTest.class);


    @Test
    public void googleSearchTest()
    {
       // PropertyConfigurator.configure("log4j.properties");
       log.info("----------------Test Case Started--------------");
        open("https://www.google.com");
        log.info("Web Page Opened");
        $(By.name("q")).setValue("Naveen AutomationLabs").pressEnter();
        log.info("search keyword has been set");
        $(By.id("logo")).shouldHave(appear);

        //Selenide implementation of assertion of test extracted from Xpath
        $(By.xpath("//h3[text()='Welcome to Naveen AutomationLabs - naveen automationlabs']")).shouldHave(Condition.text("Welcome to Naveen AutomationLabs - naveen automationlabs"));


        //Below mentioned code snipped taht was traditional testNG assertion approach
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

}
