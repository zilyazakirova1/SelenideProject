package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:9999");
        String currentDate = generateDate(4, "DD.MM.YYYY");
        $("[data-test-id='data'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='data'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Закиров-Ринатович Руслан");
        $("[data-test-id='phone'] input").setValue("+79172268902");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__current")

                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.exactText("Встреча успешно забронирована на" + currentDate));


    }

}

