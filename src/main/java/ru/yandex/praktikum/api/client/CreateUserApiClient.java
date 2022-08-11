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

    public Response authorization(CreateUserReqJson body) {
        return reqSpec
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
    }

    public void deleteUser(String token) {
        reqSpec.header("Authorization", token)
                .delete("/auth/user");
    }
}
