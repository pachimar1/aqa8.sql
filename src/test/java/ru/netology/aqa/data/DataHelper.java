package ru.netology.aqa.data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("Vasya", "qwerty123");
    }

    public static @NotNull AuthInfo getInvalidAuthInfo() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}
