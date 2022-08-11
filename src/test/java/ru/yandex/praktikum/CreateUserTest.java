package ru.yandex.praktikum;

import io.restassured.response.Response;
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
    public void creationUserTest() {
        Response response = createUserApiClient.createUser(createUserJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
}
