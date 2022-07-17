package lesson6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Тест в проверкой поиска товара с помощью датапровайдера ValueSource")
public class ValueSourceTest {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://kiberded.shop/";
        Configuration.browserSize = "1280x609";
    }
   @ValueSource(strings = {
            "Ручка КиберДеда",
            "Прозрачная капсула для дедкоина"
    })
    @ParameterizedTest (name = "Тест с проверкой поиска товара: {0}")
    void valueSourcePageTest(String testData) {
        //Presteps
        Selenide.open(Configuration.baseUrl);
        //Steps
        $("#woocommerce-product-search-field-0").setValue(testData);
        $("button[type='submit']").click();
        $("nav=[class'woocommerce-breadcrumb']");

        //Expected result
        $$(".product_title").find(Condition.
                text(testData)).shouldBe(visible);
    }
}
