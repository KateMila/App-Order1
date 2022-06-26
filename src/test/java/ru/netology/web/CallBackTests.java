package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTests {
    @Test
    void shouldSuccessfulSendForm() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Костромин Олег");
        $("[data-test-id=phone] input").setValue("+79213068666");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(text(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldGetErrorMessageIfNameInvalid() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Kostromina Vicktori");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldGetErrorMessageIfPhoneInvalid() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Костромин Олег");
        $("[data-test-id=phone] input").setValue("+7921306866600");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldInvalidCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Костромин Олег");
        $("[data-test-id=phone] input").setValue("+79213068666");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                        " и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

