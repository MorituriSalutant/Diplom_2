package ru.yandex.praktikum.api.pojo.createUser;

public class CreateUserResJson {
    private String success;
    private CreateUserReqJson user;
    private String accessToken;
    private String refreshToken;

    public CreateUserResJson() {
    }

    public CreateUserResJson(String success, CreateUserReqJson user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public CreateUserReqJson getUser() {
        return user;
    }

    public void setUser(CreateUserReqJson user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
