package ru.yandex.praktikum.api.helpers;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.pojo.createUser.UserReqJson;

public class GenerateData {
    private static String email;
    private static String password;
    private static String name;
    private static final Faker faker = new Faker();

    private static void createUserData() {
        generateEmail();
        generatePassword();
        generateName();
    }

    public static UserReqJson generateUserAccount() {
        createUserData();
        return new UserReqJson(email, password, name);
    }

    public static String generateEmail() {
        return email = faker.internet().emailAddress();
    }

    public static String generatePassword() {
        return password = faker.internet().password();
    }

    public static String generateName() {
        return name = faker.name().username();
    }

    public static void deleteUserAccount(UserReqJson userReqJson) {
        UserApiClient userApiClient = new UserApiClient();
        Response responseAuth = userApiClient.authorization(userReqJson);
        if (responseAuth.statusCode() == 200) {
            userApiClient.deleteUser();
        } else {
            System.out.println("Пользователь создан не был");
        }
    }
}
