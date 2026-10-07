package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackAndroidDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {



    @BeforeAll
    static void beforeAll(){
        Configuration.browser = BrowserstackAndroidDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
//        Configuration.pageLoadTimeout = 0;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments(){

        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);
//        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();

        Attach.addVideo(sessionId);
    }
}
