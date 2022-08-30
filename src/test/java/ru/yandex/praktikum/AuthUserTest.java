package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.user.UserReqJson;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthUserTest {

    UserApiClient userApiClient;
    UserReqJson userReqJson;

    @Before
    public void setUp(){
        userApiClient = new UserApiClient();
        userReqJson = GenerateData.generateUserAccount();
        userApiClient.createUser(userReqJson);
    }

    @Test
    public void authorizationSuccessfulTest(){
        Response response = userApiClient.authorization(userReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true))
                .body("accessToken",notNullValue())
                .body("refreshToken",notNullValue());
    }

    @Test
    public void authorizationWithInvalidData(){
        userReqJson.setName(GenerateData.generateName());
        userReqJson.setPassword(GenerateData.generatePassword());
        Response response = userApiClient.authorization(userReqJson);

        response.then()
                .assertThat()
                .statusCode(401)
                .body("success",equalTo(false))
                .body("message",equalTo("email or password are incorrect"));
    }

    @After
    public void tearDown(){
        GenerateData.deleteUserAccount(userReqJson);
    }
}
