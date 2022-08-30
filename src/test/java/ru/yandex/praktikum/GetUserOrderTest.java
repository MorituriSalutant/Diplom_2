package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.OrderApiClient;
import ru.yandex.praktikum.api.client.UserApiClient;
import ru.yandex.praktikum.api.helpers.GenerateData;
import ru.yandex.praktikum.api.pojo.user.UserReqJson;

import static org.hamcrest.CoreMatchers.*;

public class GetUserOrderTest {
    OrderApiClient orderApiClient;
    UserApiClient userApiClient;
    UserReqJson userReqJson;

    @Before
    public void setUp() {
        orderApiClient = new OrderApiClient();
        userApiClient = new UserApiClient();
        userReqJson = GenerateData.generateUserAccount();
        userApiClient.createUser(userReqJson);
    }

    @Test
    public void getAnOrderFromAnAuthorizedUserTest() {
        Response response = orderApiClient.getOrders();
        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
//                .body("orders", )
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }

    @Test
    public void getAnOrderWithoutAuthorizedUserTest() {
        userApiClient.clearToken();
        Response response = orderApiClient.getOrders();

        response.then()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @After
    public void tearDown() {
        GenerateData.deleteUserAccount(userReqJson);
    }
}
