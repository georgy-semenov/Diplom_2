package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoginUser {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private final Credentials credentials = new Credentials();
    private final UserGenerator generator = new UserGenerator();
    protected String email;
    protected String password;
    protected String accessToken;

    @Before
    @Step("create user")
    public void createUser(){
        Credentials credentials = Credentials.from(new User(RandomStringUtils.randomAlphabetic(5,6)+"@mail.ru",
                "1234", "name"));
        ValidatableResponse response = client.createNewUser(credentials);
        email = credentials.getEmail();
        password = credentials.getPassword();
        accessToken = check.createSuccessfullyToken(response);
    }
    @Test
    @Step("логин под существующим пользователем,")
    public void loginWithValidateUser(){
        credentials.setEmail(email);
        credentials.setPassword(password);
        ValidatableResponse response = client.loginUser(credentials);
        check.loginSuccessfully(response);
    }
    @Test
    @Step("логин с неверным логином и паролем.")
    public void loginWithFailDate(){
        var user = generator.generic();
        ValidatableResponse response = client.loginFailUser(user);
        check.loginWithFailDate(response);

    }
    @After
    public void tearDown(){
        if(accessToken != null ){
            client.deleteUser(accessToken);
        }
    }
}
