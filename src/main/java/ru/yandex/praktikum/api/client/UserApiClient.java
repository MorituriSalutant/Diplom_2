package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.user.UserReqJson;

public class UserApiClient extends RestAssuredClient {


    public Response createUser(UserReqJson json) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(json)
                .post("/auth/register");
        extractToken(response);
        return response;
    }

    public Response authorization(UserReqJson body) {
        Response response = reqSpec
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
        extractToken(response);
        return response;
    }

    public Response changeDataUser(UserReqJson body) {
        return reqSpec
                .header("Authorization", RestAssuredClient.getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .patch("/auth/user");
    }

    public void deleteUser() {
        reqSpec.header("Authorization", RestAssuredClient.getToken())
                .delete("/auth/user");
    }

    public void clearToken(){
        RestAssuredClient.clearAuthToken();
    }

    private void extractToken(Response response) {
        if (response.statusCode() == 200) {
            String token = response.then().extract().body().path("accessToken");
            RestAssuredClient.setToken(token);
        } else {
            clearToken();
        }

    }
}
