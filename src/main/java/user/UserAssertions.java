package user;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class UserAssertions {
    public String createSuccessfullyToken(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
    }

    public void createWithFamousLogin(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("success", equalTo(false));
    }
    public void createUserWithOutOneField(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }
    public void loginSuccessfully(ValidatableResponse response){
          response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));

    }
    public void loginWithFailDate(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("email or password are incorrect"));
    }
    public void changeEmailAuthorization(ValidatableResponse response, String email){
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("user.email", equalTo(email));
    }
    public void changeNamedAuthorization(ValidatableResponse response, String name){
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("user.name", equalTo(name));
    }
    public void changeDataWithOutAuthoriz(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("You should be authorised"));
    }

}
