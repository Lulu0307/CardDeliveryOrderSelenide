import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestDeliveryApp2 {

    LocalDate date = LocalDate.now();
    LocalDate deliveryDate = date.plusDays(7);
    int day = deliveryDate.getDayOfMonth();

    void shouldDetermineMonth() {
        if (date.getMonth() != deliveryDate.getMonth()) {
            $$(By.cssSelector(".calendar__arrow_direction_right")).get(1).click();
            $(byText(String.valueOf(day))).waitUntil(visible, 10000).click();
        } else {
            $(byText(String.valueOf(day))).waitUntil(visible, 5000).click();
        }
    }

    @Test
    void shouldUseDropDownListsAndIcons() {
        open("http://localhost:9999");
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id=city] input").setValue("мо");
        $(withText("Москва")).waitUntil(visible, 5000).click();
        form.$(".input__icon").waitUntil(visible, 5000).click();
        shouldDetermineMonth();
        form.$("[data-test-id = name] input").setValue("Ваня Ванин");
        form.$("[data-test-id = phone] input").setValue("+78005553535");
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(String.valueOf(day))).waitUntil(visible, 15000);

    }

}
