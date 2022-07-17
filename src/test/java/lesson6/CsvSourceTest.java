package lesson6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Тест в проверкой поиска товара с помощью датапровайдера CscSource")
public class CsvSourceTest {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://kiberded.shop/";
        Configuration.browserSize = "1280x609";
    }
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "Ручка КиберДеда, В корзину",
            "Прозрачная капсула для дедкоина, В корзину"
    }
    )
    @ParameterizedTest(name = "Тест с проверкой поиска товара и отображением \"В коризну\":: {0}, ожидаем: {1}")
    void csvSourcePageTest(String testData, String expectedResult) {
        //Presteps
        Selenide.open(Configuration.baseUrl);
        //Steps
        $("#woocommerce-product-search-field-0").setValue(testData);
        $("button[type='submit']").click();
        $("nav=[class'woocommerce-breadcrumb']");

        //Expected result
        $$(".summary").find(Condition.
                text(testData)).shouldBe(visible);
    }
}
