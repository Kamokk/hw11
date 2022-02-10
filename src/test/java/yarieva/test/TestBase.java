package yarieva.test;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import yarieva.config.CredentialsConfig;
import yarieva.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        String login = credentials.login();
        String password = credentials.password();
        String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");

        Configuration.browserSize = "1920x1080";
        Configuration.remote = format("https://%s:%s@%s", login, password, url);


        Configuration.baseUrl = "https://demoqa.com";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}