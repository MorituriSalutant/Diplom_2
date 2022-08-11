package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.createUser.CreateUserReqJson;

public class CreateUserApiClient extends RestAssuredClient {

    public Response createUser(CreateUserReqJson json) {
        return reqSpec
                .contentType(ContentType.JSON)
                .body(json)
                .post("/auth/register");
    }
}
