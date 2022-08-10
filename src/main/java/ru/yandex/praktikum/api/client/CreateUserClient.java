package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.CreateUserJson;

public class CreateUserClient extends RestAssuredClient {

    public Response createUser(CreateUserJson json) {
        return reqSpec
                .contentType(ContentType.JSON)
                .body(json)
                .post("/auth/register");
    }
}
