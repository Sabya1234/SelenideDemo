package org.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
public class HandleIframeTest {

    Logger log= Logger.getLogger(HandleIframeTest.class);

    @Test
    public void verifyIframeOperation() throws InterruptedException {
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";

        open("https://allwebco-templates.com/support/S_script_IFrame.htm");
        Thread.sleep(3000);

        switchTo().frame($(By.xpath("//iframe[@name='Framename' and @class='framesample framesample2']")));

        log.info("into child frame");
        $(By.xpath("//img[@alt='Search']")).click();

        ElementsCollection items = $$(By.xpath("//input[@name='terms']"));

        int size= items.size();
        log.info("no of textbox in current frame is:"+size);
        items.get(size-1).setValue("Testing");
        $(By.xpath("//input[@name='submitbutton']")).click();
        Thread.sleep(2000);

        //switching back  to main html from inner frame
        switchTo().defaultContent();
        log.info("in main html");

        $(By.xpath("//span[text()='Sample website in an IFrame page']")).shouldHave(Condition.appear);


    }
}
