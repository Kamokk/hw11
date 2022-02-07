package yarieva.test.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {


    @Test
    @Tag("properties")
    void setBrowserProperties() {
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "91");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        System.out.println(browser);
        System.out.println(version);
        System.out.println(browserSize);

        // gradle clean properties_tests -Dbrowser=opera -Dversion=93
    }
}
