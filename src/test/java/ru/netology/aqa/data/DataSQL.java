package ru.netology.aqa.data;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSQL {

    private static String getIdUserFor(DataHelper.AuthInfo authInfo) throws SQLException {
        var idUserQuery = "SELECT id FROM users WHERE login=" + '"' + authInfo.getLogin() + '"' + ";";
        try (var connection = getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(idUserQuery)) {
            rs.next();
            return rs.getString("id");
        }
    }

    public static DataHelper.VerificationCode getVerificationCodeFor(DataHelper.AuthInfo authInfo) throws SQLException {
        var codeUserQuery = "SELECT code FROM auth_codes WHERE user_id=" + '"' + getIdUserFor(authInfo) + '"' + " ORDER BY created DESC;";
        try (var connection = getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(codeUserQuery)) {
            rs.next();
            String codeUser = rs.getString("code");
            return new DataHelper.VerificationCode(codeUser);
        }
    }

    public static void cleanTables() throws SQLException {
        var cleanAuthCodes = "DELETE FROM auth_codes;";
        var cleanCardTransactions = "DELETE FROM card_transactions;";
        var cleanCards = "DELETE FROM cards;";
        var cleanUsers = "DELETE FROM users;";
        try (var connection = getConnection();
             var statement = connection.createStatement()) {
            statement.execute(cleanAuthCodes);
            statement.execute(cleanCardTransactions);
            statement.execute(cleanCards);
            statement.execute(cleanUsers);
        }
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
    }
}

