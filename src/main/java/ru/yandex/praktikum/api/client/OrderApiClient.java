package ru.yandex.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.api.pojo.order.OrderReqJson;

public class OrderApiClient extends RestAssuredClient {

    public Response getListIngredients() {
        return reqSpec.get("/ingredients");
    }

    public Response createOrder(OrderReqJson body) {
        return reqSpec
                .header("Authorization", super.bearerToken)
                .contentType(ContentType.JSON)
                .body(body)
                .post("/orders");
    }
}
