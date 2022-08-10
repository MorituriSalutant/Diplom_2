package ru.yandex.praktikum.api.helpers;

import com.github.javafaker.Faker;
import ru.yandex.praktikum.api.pojo.CreateUserJson;

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

    public static CreateUserJson generateUserAccount(){
        createAccountData();
        return new CreateUserJson(email,password,name);
    }
}
