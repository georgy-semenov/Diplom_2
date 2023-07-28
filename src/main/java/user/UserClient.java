package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClient extends Client{
    static final String CREATE_USER_API = "/auth/register";
    static final String LOGIN_USER_API = "/auth/login";
    static final String DELETE_USER_API = "/auth/user";
    static final String CHANGE_USER_API = "/auth/user";
    @Step("создание пользователя")
    public ValidatableResponse createUser(User user){
        return spec()
                .body(user)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }
    @Step("создание существующего пользователя")
    public ValidatableResponse createUserWithFamousLogin(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }
    @Step("создание нового уникального пользователя")
    public ValidatableResponse createNewUser(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(CREATE_USER_API)
                .then().log().all();
    }
    @Step("авторизация пользователя")
    public ValidatableResponse loginUser(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .post(LOGIN_USER_API)
                .then().log().all();
    }
    @Step("авторизация пользователя с неверными данными")
    public ValidatableResponse loginFailUser(User user){
        return spec()
                .body(user)
                .when()
                .post(LOGIN_USER_API)
                .then().log().all();
    }
    @Step("удаление пользователя")
    public ValidatableResponse deleteUser(String token){
        return spec()
                .header("authorization", token)
                .delete(DELETE_USER_API)
                .then().log().all();
    }
    @Step("смена данных у пользователя с авторизацией")
    public ValidatableResponse changeUserDataWithAuthoriz(String token, Credentials credentials){
        return spec()
                .header("authorization", token)
                .body(credentials)
                .when()
                .patch(CHANGE_USER_API)
                .then().log().all();
    }
    @Step("смена данных пользователя без авторизации")
    public ValidatableResponse changeUserWithoutAuthoriz(Credentials credentials){
        return spec()
                .body(credentials)
                .when()
                .patch(CHANGE_USER_API)
                .then().log().all();
    }
}
