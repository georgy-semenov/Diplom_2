package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class TestCreateUser {
    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected String accessToken;

    @Test
    @Step("создать уникального пользователя")
    public void createUserSuccessfully(){
        var user = generator.random();
        ValidatableResponse response = client.createUser(user);
        accessToken = check.createSuccessfullyToken(response);
    }
    @Test
    @Step("создать пользователя, который уже зарегистрирован")
    public void createUserWithLasLogin(){
        var user = generator.random();
        ValidatableResponse resp = client.createUser(user);
        accessToken = check.createSuccessfullyToken(resp);

        Credentials credentials = Credentials.from(user);
        ValidatableResponse response = client.createUserWithFamousLogin(credentials);
        check.createWithFamousLogin(response);
    }
    @Test
    @Step("создать пользователя и не заполнить email")
    public void createUserWithOutEmail(){
        var user = generator.genericWithOutEmail();
        ValidatableResponse response = client.createUser(user);
        check.createUserWithOutOneField(response);
    }
    @Test
    @Step("создать пользователя и не заполнить password")
    public void createUserWithOutPassword(){
        var user = generator.genericWithOutPassword();
        ValidatableResponse response = client.createUser(user);
        check.createUserWithOutOneField(response);
    }
    @Test
    @Step("создать пользователя и не заполнить name")
    public void createUserWithOutName(){
        var user = generator.genericWithOutName();
        ValidatableResponse response = client.createUser(user);
        check.createUserWithOutOneField(response);
    }
    @After
    public void tearDown(){
        if(accessToken != null) {
            client.deleteUser(accessToken);
        }
    }
}
