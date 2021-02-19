
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDeliveryApp {

    LocalDate date = LocalDate.now();
    private String deliveryDate = date.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void shouldSendOrder(){
        open("http://localhost:9999");
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id=city] input").setValue("Кемерово");
        form.$("[data-test-id = date] input").setValue("22.02.2021");
        form.$("[data-test-id = name] input").setValue("Ваня Ванин");
        form.$("[data-test-id = phone] input").setValue("+78005553535");
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(deliveryDate)).waitUntil(visible, 15000);

    }
}
