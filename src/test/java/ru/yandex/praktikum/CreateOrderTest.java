package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.client.OrderApiClient;
import ru.yandex.praktikum.api.helpers.GenerateOrder;
import ru.yandex.praktikum.api.pojo.order.OrderReqJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@DisplayName("Создание заказа")
public class CreateOrderTest {

    OrderReqJson orderReqJson;
    OrderApiClient orderApiClient;

    @Before
    public void setUp() {
//        orderApiClient = new OrderApiClient();
//        orderReqJson = GenerateOrder.createOrderJson();
    }

    @Test
    @DisplayName("Получение списка ингредиентов")
    public void getListIngredientsTest() {
        Response response = orderApiClient.getListIngredients();

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("data", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWitIngredientsTest() {
        Response response = orderApiClient.createOrder(orderReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void createOrderWitIngredientsAndAuthTest() {
        Response response = orderApiClient.createOrder(orderReqJson);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        orderReqJson.setIngredients(new ArrayList<>());
        Response response = orderApiClient.createOrder(orderReqJson);

        response.then()
                .assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным айди ингредиентов")
    public void createOrderWithoutBadIdIngredientsTest() {
        orderReqJson.setIngredients(List.of("61c0c5a71d1f82001bdbaa6d"));
        Response response = orderApiClient.createOrder(orderReqJson);

        response.then()
                .assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("One or more ids provided are incorrect"));
    }


}
