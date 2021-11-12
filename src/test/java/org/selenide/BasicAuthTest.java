package org.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.*;
public class BasicAuthTest {
    Logger log = Logger.getLogger(BasicAuthTest.class);

    private static final String username="admin";
    private static final String password="admin";

    @Test
    public void basicAuthValidation() throws MalformedURLException {

        System.setProperty("selenide.browser","firefox");
        //open("https://the-internet.herokuapp.com/basic_auth","","admin","admin");

        open(new URL("https://the-internet.herokuapp.com/basic_auth"),"",username,password);
       String textFromPage= $("div.example").getText();

        Assert.assertTrue(textFromPage.contains("Congratulations"));


    }

    /**
     * with traditional WebDriver selenium 4
     */
    @Test
    public void validateBaseAuthUsingDevtools() throws EncoderException {


       // open();
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        DevTools devtool= ((ChromeDriver)driver).getDevTools();
        devtool.createSession();
        devtool.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        Map<String,Object> headers = new HashMap<>();
        String basicAuth= "Basic "+new String(new Base64().encode(String.format("%s:%s",username,password).getBytes()));
        log.info("The Base64 encoded value is:"+basicAuth);
        headers.put("Authorization",basicAuth);
        devtool.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        String textFromPage= $("div.example").getText();

        Assert.assertTrue(textFromPage.contains("Congratulations"));

    }
}
