package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class TestChangeUserData {
    private final Credentials credentials = new Credentials();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected String email;
    protected String name;
    protected String accessToken;
    protected String newEmail = (RandomStringUtils.randomAlphabetic(5,6)+"@mail.ru").toLowerCase();
    protected String newName = "new Name";

    @Before
    @Step("create user")
    public void createUser(){
        Credentials credentials = Credentials.from(new User(RandomStringUtils.randomAlphabetic(5,6)+"@mail.ru",
                "1234", "name"));
        ValidatableResponse response = client.createNewUser(credentials);
        email = credentials.getEmail();
        name = credentials.getName();
        accessToken = check.createSuccessfullyToken(response);
    }
    @After
    @Step("delete user")
    public void tearDown(){
        if(accessToken != null ){
            client.deleteUser(accessToken);
        }
    }
    @Test
    @Step("change email with authorization")
    public void changeEmailWithAuthorization(){
        credentials.setEmail(newEmail);
        ValidatableResponse response = client.changeUserDataWithAuthoriz(accessToken,credentials);
        check.changeEmailAuthorization(response, newEmail);
    }
    @Test
    @Step("change name with authorization")
    public void changePasswordWithAuthorization(){
        credentials.setName(newName);
        ValidatableResponse response = client.changeUserDataWithAuthoriz(accessToken, credentials);
        check.changeNamedAuthorization(response, newName);
    }
    @Test
    @Step("change email without authorization")
    public void changeEmailWithOutAuthoriz(){
        credentials.setEmail(newEmail);
        ValidatableResponse response = client.changeUserWithoutAuthoriz(credentials);
        check.changeDataWithOutAuthoriz(response);
    }
    @Test
    @Step("change name without authorization")
    public void changeNameWithOutAuthoriz(){
        credentials.setName(newName);
        ValidatableResponse response = client.changeUserWithoutAuthoriz(credentials);
        check.changeDataWithOutAuthoriz(response);
    }
}
