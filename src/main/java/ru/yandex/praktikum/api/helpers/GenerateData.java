package ru.yandex.praktikum.api.helpers;

import com.github.javafaker.Faker;
import ru.yandex.praktikum.api.pojo.createUser.CreateUserReqJson;

public class GenerateData {
    private static String email;
    private static String password;
    private static String name;

    private static void createAccountData(){
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        name = faker.name().username();
    }

    public static CreateUserReqJson generateUserAccount(){
        createAccountData();
        return new CreateUserReqJson(email,password,name);
    }
}
