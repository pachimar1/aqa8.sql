package ru.netology.aqa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.aqa.data.DataHelper;
import ru.netology.aqa.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class RegistrationTest {


    @AfterAll
    static void shouldCleanDB() {
        DataHelper.cleanTables();
    }

    @Test
    void shouldOpenDashboard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.checkHeading();
    }

    @Test
    void shouldBlockedSystem() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.invalidLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.invalidLogin(authInfo);
        loginPage.systemBlocked();
    }
}

