package order;

import io.restassured.response.ValidatableResponse;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

public class OrderAssertions {
    public List<String> getSuccessfullyIngredients(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true))
                .extract()
                .path("data._id");
    }
    public void createSuccessfullyOrderWithAutorization(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));
    }
    public void createSuccessOrderWithOutAuth(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("order.number", notNullValue());
    }
    public void createOrderWithOutIngredients(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Ingredient ids must be provided"));
    }
    public void createOrderWithFailHash(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_INTERNAL_ERROR);
    }
    public void getOrderWithOutAuthorization(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message",  equalTo("You should be authorised"));
    }
    public String getSuccessfullyOrderWithAutoriz(ValidatableResponse response){
       return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true))
                .extract()
                .path("order._id");
    }
    public List<String> getSuccessfullyOrderWithAutorization(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true))
                .extract()
                .path("orders._id");
    }
}
