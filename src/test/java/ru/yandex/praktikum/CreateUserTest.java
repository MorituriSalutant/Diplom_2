package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.CreateUserClient;
import ru.yandex.praktikum.api.pojo.CreateUserJson;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {

    CreateUserJson createUserJson;
    CreateUserClient createUserClient;


    @Before
    public void setUp() {
        createUserJson = new CreateUserJson("a11s1d123312@asd.asd", "as1dfh1231", "as1das");
        createUserClient = new CreateUserClient();

    }

    @Test
    public void creationUserTest() {
        Response response = createUserClient.createUser(createUserJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
}
