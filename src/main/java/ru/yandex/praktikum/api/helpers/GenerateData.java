package ru.yandex.praktikum.api.helpers;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.client.CreateUserApiClient;
import ru.yandex.praktikum.api.pojo.createUser.CreateUserReqJson;

public class GenerateData {
    private static String email;
    private static String password;
    private static String name;

    private static void createUserData() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        name = faker.name().username();
    }

    public static CreateUserReqJson generateUserAccount() {
        createUserData();
        return new CreateUserReqJson(email, password, name);
    }

    public static void deleteUserAccount() {
        CreateUserApiClient createUserApiClient = new CreateUserApiClient();
        CreateUserReqJson createUserReqJson = new CreateUserReqJson(email, password, name);
        Response responseAuth = createUserApiClient.authorization(createUserReqJson);
        if (responseAuth.statusCode() == 200) {
            String token = responseAuth.then().extract().path("accessToken");
            createUserApiClient.deleteUser(token);
        } else {
            System.out.println("Пользователь создан не был");
        }
    }
}
