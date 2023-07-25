package order;

import user.Client;
import io.restassured.response.ValidatableResponse;
import user.Credentials;


public class OrderClient extends Client {
    static final String CREATE_ORDER_API = "/orders";
    static final String INGREDIENTS_API = "/ingredients";
    static final String GET_ORDER_API ="/orders";

    public ValidatableResponse getAllIngredients(){
        return spec()
                .get(INGREDIENTS_API)
                .then().log().all();
    }
    public ValidatableResponse createOrderAuth(String token, Order order){
        return spec()
                .header("authorization", token)
                .body(order)
                .when()
                .post(CREATE_ORDER_API)
                .then().log().all();
    }
    public ValidatableResponse createOrderWithOutAuth(Order order){
        return spec()
                .body(order)
                .when()
                .post(CREATE_ORDER_API)
                .then().log().all();
    }
    public ValidatableResponse getOrderWithOutAuth(){
        return spec()
                .get(GET_ORDER_API)
                .then().log().all();
    }
    public ValidatableResponse getOrderWithAuth(String token){
        return spec()
                .header("authorization", token)
                .when()
                .get(GET_ORDER_API)
                .then().log().all();
    }
}
