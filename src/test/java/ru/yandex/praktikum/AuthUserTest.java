package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.CreateUserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.createUser.CreateUserReqJson;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthUserTest {

    CreateUserApiClient createUserApiClient;
    CreateUserReqJson createUserReqJson;

    @Before
    public void setUp(){
        createUserApiClient = new CreateUserApiClient();
        createUserReqJson = GenerateData.generateUserAccount();
        createUserApiClient.createUser(createUserReqJson);
    }

    @Test
    public void authorizationSuccessfulTest(){
        Response response = createUserApiClient.authorization(createUserReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true))
                .body("accessToken",notNullValue())
                .body("refreshToken",notNullValue());
    }

    @Test
    public void authorizationWithInvalidData(){
        createUserReqJson.setName("test");
        createUserReqJson.setPassword("test");
        Response response = createUserApiClient.authorization(createUserReqJson);

        response.then()
                .assertThat()
                .statusCode(401)
                .body("success",equalTo(false))
                .body("message",equalTo("email or password are incorrect"));
    }

    @After
    public void tearDown(){
        GenerateData.deleteUserAccount();
    }
}
