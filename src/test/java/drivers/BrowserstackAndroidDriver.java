package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigAndroid;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackAndroidDriver implements WebDriverProvider {

    private static final ConfigAndroid cfg = ConfigFactory.create(ConfigAndroid.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("platformName", "Android");

        // Настройки Appium (берём из конфига)
        caps.setCapability("appium:app", cfg.androidApp());
        caps.setCapability("appium:automationName", "UIAutomator2");
        caps.setCapability("appium:deviceName", cfg.androidDeviceName());
        caps.setCapability("appium:platformVersion", cfg.androidOsVersion());

        Map<String, Object> bstackOptions = new HashMap<>();

        // ️ ВРЕМЕННЫЙ ХАРДКОД КЛЮЧЕЙ (чтобы проверить работу BrowserStack)
        bstackOptions.put("userName", "mimimurmur_j6r0lN");
        bstackOptions.put("accessKey", "y3exSpxS3Qf8e7pz53Jv");

        // Остальное берём из конфига
        bstackOptions.put("projectName", cfg.projectName());
        bstackOptions.put("buildName", cfg.buildName());
        bstackOptions.put("sessionName", "android_search_test");
        bstackOptions.put("debug", true);
        bstackOptions.put("networkLogs", true);

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка URL BrowserStack", e);
        }
    }
}