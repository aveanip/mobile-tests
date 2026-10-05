package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        // 1. Используем современный класс MutableCapabilities
        MutableCapabilities caps = new MutableCapabilities();

        // 2. Стандартные настройки для Android и приложения Wikipedia
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "bs://af912a5f18cb92b0be4a6556ffe08ed135d18e6d"); // Рабочий ID Wikipedia
        caps.setCapability("appium:deviceName", "Google+Pixel+6");
        caps.setCapability("appium:osVersion", "12.0");

        // 3. ВАЖНО: Учетные данные прячем ВНУТРЬ специальной опции "bstack:options"
        MutableCapabilities browserstackOptions = new MutableCapabilities();
        browserstackOptions.setCapability("userName", "aveanip_2F0oHp"); // Твой логин
        browserstackOptions.setCapability("accessKey", "Yapk5XvyyzgGrocsdyEn"); // <-- Вставь сюда свой Access Key из аккаунта!
        browserstackOptions.setCapability("projectName", "First Java Project");
        browserstackOptions.setCapability("buildName", "browserstack-build-1");
        browserstackOptions.setCapability("sessionName", "first_test");

        // 4. Кладем эту "коробку" внутрь основных настроек
        caps.setCapability("bstack:options", browserstackOptions);

        // 5. Запускаем драйвер
        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка URL BrowserStack", e);
        }
    }
}

