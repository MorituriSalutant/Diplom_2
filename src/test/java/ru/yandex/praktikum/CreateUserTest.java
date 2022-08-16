package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.createUser.UserReqJson;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {

    UserReqJson userReqJson;
    UserApiClient userApiClient;


    @Before
    public void setUp() {
        userApiClient = new UserApiClient();
        userReqJson = GenerateData.generateUserAccount();

    }

    @Test
    public void createSuccessUserTest() {
        Response response = userApiClient.createUser(userReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void createUserWhoAlreadyExistTest(){
        userApiClient.createUser(userReqJson); //Создали пользователя
        Response response = userApiClient.createUser(userReqJson);//Повторно отправили запрос на создание

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success",equalTo(false))
                .body("message",equalTo("User already exists"));

    }

    @Test
    public void createUserWithoutEmailTest(){
        userReqJson.setEmail(null); //Удалили почту
        Response response = userApiClient.createUser(userReqJson);//Отправили запрос без почты

        response.then()
                .assertThat()
                .statusCode(403)
                .body("success",equalTo(false))
                .body("message",equalTo("Email, password and name are required fields"));
    }

    @After
    public void tearDown(){
        GenerateData.deleteUserAccount(userReqJson);
    }
}
