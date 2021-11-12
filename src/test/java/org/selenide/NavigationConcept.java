package org.selenide;

import com.codeborne.selenide.Configuration;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

/**
 * using selenide we can achieve back and forward simulation
 * Testcase step- opening google.com and then we will open amazon .com then back to google verify title
 * and then move forward and verify title and then again back to google and refresh the page
 */
public class NavigationConcept {

    Logger log = Logger.getLogger(NavigationConcept.class);
    @Test
    public void navigationTest()
    {
        log.info("----------------Test Case Started---------------------");
        Configuration.browserSize="1920x1080";

        open("https://www.google.com/");
        log.info("navigates to the Webpage");
        verifyTitle("Google");
        open("https://www.amazon.com/");
        verifyTitle("Amazon");
        back();
        verifyTitle("Google");
        forward();
        verifyTitle("Amazon");
        back();
        verifyTitle("Google");
        refresh();

    }

    public void verifyTitle(String expectedTitle)
    {

        if (title().contains(expectedTitle))
        {
            log.info("Title matched we are on::"+title());
        }
        else
        {
            log.error("wrong webpage");
        }

    }



}



