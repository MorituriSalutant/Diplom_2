package ru.yandex.praktikum.api.helpers;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.pojo.createUser.UserReqJson;

public class GenerateData {
    private static String email;
    private static String password;
    private static String name;

    private static void createUserData() {
        Faker faker = new Faker();
        generateEmail();
        generatePassword();
        generateName();
    }

    public static UserReqJson generateUserAccount() {
        createUserData();
        return new UserReqJson(email, password, name);
    }

    public static String generateEmail(){
        Faker faker = new Faker();
        return email = faker.internet().emailAddress();
    }
    public static String generatePassword(){
        Faker faker = new Faker();
        return password = faker.internet().password();
    }
    public static String generateName(){
        Faker faker = new Faker();
        return name = faker.name().username();
    }


    public static void deleteUserAccount(UserReqJson userReqJson) {
        UserApiClient userApiClient = new UserApiClient();
        Response responseAuth = userApiClient.authorization(userReqJson);
        if (responseAuth.statusCode() == 200) {
            String token = responseAuth.then().extract().path("accessToken");
            userApiClient.deleteUser();
        } else {
            System.out.println("Пользователь создан не был");
        }
    }
}
