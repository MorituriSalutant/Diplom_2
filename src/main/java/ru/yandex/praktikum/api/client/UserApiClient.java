package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.user.UserReqJson;

public class UserApiClient extends RestAssuredClient {

    protected String token;

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
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .patch("/auth/user");
    }

    public void deleteUser() {
        reqSpec.auth().oauth2(token)
                .delete("/auth/user");
    }

    public UserApiClient clearAuthToken(){
            token = "";
        return this;
    }

    private void extractToken(Response response){
        token = response.then().extract().body().path("accessToken");
        token = token.replace("Bearer ","");
    }
}
