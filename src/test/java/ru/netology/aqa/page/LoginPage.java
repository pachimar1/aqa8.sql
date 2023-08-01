package ru.netology.aqa.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.aqa.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement notificationTitle = $("[data-test-id=error-notification] .notification__title");
    private SelenideElement notificationContent = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        notificationTitle.shouldHave(text("Ошибка"));
        notificationContent.shouldHave(text("Неверно указан логин или пароль"));
    }

    public void systemBlocked() {
        notificationTitle.shouldHave(text("Ошибка"));
        notificationContent.shouldHave(text("Повторный вход возможен через 30 минут"));
    }

    public void cleanLoginFields() {
        loginField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }
}
