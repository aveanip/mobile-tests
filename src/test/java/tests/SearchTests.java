package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        $(AppiumBy.xpath("//android.widget.Button[@content-desc='Next']")).click();
        step("Открыть поиск и ввести текст в поле ввода", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверить, что появились результаты поиска", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }


    @Test
    void openingArticle() {
        step("Открыть поиск и ввести текст в поле ввода", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Lev Tolstoy");
        });
        step("Проверить наличие результатов поиска больше 0", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0));
        });
        step("Открыть статью, и проверить результат", () -> {
            $$(id("org.wikipedia.alpha:id/lead_web_view"))
                    .first()
                    .click();
        });
    }
}


