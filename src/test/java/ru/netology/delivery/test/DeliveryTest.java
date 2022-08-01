package ru.netology.delivery.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.Click;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.SendKeysAction;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

class DeliveryTest {

    @BeforeEach
    void form() {
        open("http://localhost:9999");
        int daysToAddForFirstMeeting = 4;
        LocalDate borderThreeDays = LocalDate.now().plusDays(3);
        LocalDate borderWeek = LocalDate.now().plusDays(7);
        $("[data-test-id=date] .input__icon").click();
        if (borderThreeDays.getMonthValue() != borderWeek.getMonthValue()) {
            $("[data-step='1']").click();
        }
        $$("tr td").findBy(text(String.valueOf(LocalDate.now().plusDays(daysToAddForFirstMeeting).getDayOfMonth()))).click();
        $("[data-test-id=agreement]").click();
    }

    static void clearDate() {
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(BACK_SPACE);
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $x("//button[contains(@class, 'button_view_extra')]").click();
        clearDate();
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $x("//button[contains(@class, 'button_view_extra')]").click();
        $$("[data-test-id=replan-notification] button").get(1).click();
        $("[data-test-id=success-notification] [class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    void shouldSuccessfulFromCustomValidUser() {
        var customUser = DataGenerator.CustomRegistration.generateCustomUser();
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(customUser.getCity());
        $("[data-test-id=name] input").setValue(customUser.getName());
        $("[data-test-id=phone] input").setValue(customUser.getPhone());
        $x("//button[contains(@class, 'button_view_extra')]").click();
        $$("[data-test-id=replan-notification] button").get(1).click();
        $("[data-test-id=success-notification] [class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
