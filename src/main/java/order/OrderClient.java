package order;

import io.qameta.allure.Step;
import user.Client;
import io.restassured.response.ValidatableResponse;



public class OrderClient extends Client {
    static final String CREATE_ORDER_API = "/orders";
    static final String INGREDIENTS_API = "/ingredients";
    static final String GET_ORDER_API ="/orders";

    @Step("получение всех ингредиентов")
    public ValidatableResponse getAllIngredients(){
        return spec()
                .get(INGREDIENTS_API)
                .then().log().all();
    }
    @Step("создание закза с авторизацией")
    public ValidatableResponse createOrderAuth(String token, Order order){
        return spec()
                .header("authorization", token)
                .body(order)
                .when()
                .post(CREATE_ORDER_API)
                .then().log().all();
    }
    @Step("создание заказа без авторизации")
    public ValidatableResponse createOrderWithOutAuth(Order order){
        return spec()
                .body(order)
                .when()
                .post(CREATE_ORDER_API)
                .then().log().all();
    }
    @Step("получение заказа без авторизации")
    public ValidatableResponse getOrderWithOutAuth(){
        return spec()
                .get(GET_ORDER_API)
                .then().log().all();
    }
    @Step("получение заказа с авторизацией")
    public ValidatableResponse getOrderWithAuth(String token){
        return spec()
                .header("authorization", token)
                .when()
                .get(GET_ORDER_API)
                .then().log().all();
    }
}
