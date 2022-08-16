package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.createUser.UserReqJson;

public class UserApiClient extends RestAssuredClient {

    protected String token;

    public Response createUser(UserReqJson json) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(json)
                .post("/auth/register");
        token = response.then().extract().body().path("accessToken");
        return response;
    }

    public Response authorization(UserReqJson body) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
        token = response.then().extract().body().path("accessToken");
        return response;
    }

    public Response changeDataUser(UserReqJson body) {
        return reqSpec
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(body)
                .patch("/auth/user");
    }

    public void deleteUser() {
        reqSpec.header("Authorization", token)
                .delete("/auth/user");
    }
}
