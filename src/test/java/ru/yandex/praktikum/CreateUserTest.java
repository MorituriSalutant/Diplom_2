package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.CreateUserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.createUser.CreateUserReqJson;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {

    CreateUserReqJson createUserJson;
    CreateUserApiClient createUserApiClient;


    @Before
    public void setUp() {
        createUserApiClient = new CreateUserApiClient();
        createUserJson = GenerateData.generateUserAccount();

    }

    @Test
    public void createSuccessUserTest() {
        Response response = createUserApiClient.createUser(createUserJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void createUserWhoAlreadyExistTest(){
        createUserApiClient.createUser(createUserJson); //Создали пользователя
        Response response = createUserApiClient.createUser(createUserJson);//Повторно отправили запрос на создание

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success",equalTo(false))
                .body("message",equalTo("User already exists"));

    }

    @Test
    public void createUserWithoutEmailTest(){
        createUserJson.setEmail(null); //Удалили почту
        Response response = createUserApiClient.createUser(createUserJson);//Отправили запрос без почты

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success",equalTo(false))
                .body("message",equalTo("Email, password and name are required fields"));
    }

    @After
    public void tearDown(){
        GenerateData.deleteUserAccount();
    }
}
