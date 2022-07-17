package lesson6;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MethodSourceTest {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://kiberded.shop/";
        Configuration.browserSize = "1280x609";
    }

    static Stream<Arguments> methodSourcePageTest() {
        return Stream.of(
                Arguments.of( "Ручка КиберДеда", "Брендовая, суперлегкая ручка КиберДеда с автоматическим поворотным механизмом. Цвет чернил синий. Размер 132х8мм."),
                Arguments.of("Прозрачная капсула для дедкоина", "Лучшая защита вашей монеты. Подходит для монет диаметром 40мм.")
        );
    }

    @MethodSource()
    @ParameterizedTest(name = "Тест с проверкой поиска товара: {0}, ожидаем отображение знака валюты: {1}")
    void methodSourcePageTest(String testData, String expectedResult) {
        //Prestep
        Selenide.open(Configuration.baseUrl);
        //Steps
        $("#woocommerce-product-search-field-0").setValue(testData);
        $("button[type='submit']").click();
        //Expected result
        $$(".woocommerce-Tabs-panel").find(Condition.
                text(expectedResult)).shouldBe(visible);
    }
}
