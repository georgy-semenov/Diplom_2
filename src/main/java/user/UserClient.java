package user;

import io.restassured.response.ValidatableResponse;

public class UserClient extends Client{
    static final String CREATE_USER_API = "/auth/register";
    static final String LOGIN_USER_API = "/auth/login";
    static final String DELETE_USER_API = "/auth/user";
    static final String CHANGE_USER_API = "/auth/user";
    public ValidatableResponse createUser(User user){
        return spec()
                .body(user)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }

    public ValidatableResponse createUserWithFamousLogin(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }
    public ValidatableResponse createNewUser(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }
    public ValidatableResponse loginUser(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(LOGIN_USER_API)
                .then().log().all();
    }
    public ValidatableResponse loginFailUser(User user){
        return spec()
                .body(user)
                .when()
                .post(LOGIN_USER_API)
                .then().log().all();
    }
    public ValidatableResponse deleteUser(String token){
        return spec()
                .header("authorization", token)
                .delete(DELETE_USER_API)
                .then().log().all();
    }
    public ValidatableResponse changeUserDataWithAuthoriz(String token, Credentials credentials){
        return spec()
                .header("authorization", token)
                .body(credentials)
                .when()
                .patch(CHANGE_USER_API)
                .then().log().all();
    }
    public ValidatableResponse changeUserWithoutAuthoriz(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .patch(CHANGE_USER_API)
                .then().log().all();
    }
}
