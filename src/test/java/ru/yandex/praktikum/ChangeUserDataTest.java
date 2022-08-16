package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.createUser.UserReqJson;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ChangeUserDataTest {
    UserApiClient userApiClient;
    UserReqJson userReqJson;

    @Before
    public void setUp(){
        userApiClient = new UserApiClient();
        userReqJson = GenerateData.generateUserAccount();
        userApiClient.createUser(userReqJson);
        userApiClient.authorization(userReqJson);
    }

    @Test
    public void changeUserDataNameTest(){
        userReqJson.setName(GenerateData.generateName());
        Response response = userApiClient.changeDataUser(userReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user",notNullValue());
    }

    @Test
    public void changeUserDataEmailTest(){
        userReqJson.setEmail(GenerateData.generateEmail());
        Response response = userApiClient.changeDataUser(userReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user",notNullValue());
    }

    @After
    public void tearDown(){
        GenerateData.deleteUserAccount(userReqJson);
    }
}