package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.Credentials;
import user.User;
import user.UserAssertions;
import user.UserClient;

import java.util.Collections;
import java.util.List;

public class TestOrderCreate {
    private OrderAssertions check = new OrderAssertions();
    private OrderClient client = new OrderClient();
    private UserClient userClient = new UserClient();
    private UserAssertions checkUser = new UserAssertions();
    protected List<String> ingredients;
    protected String accessToken;


    @Before
    @Step("получение id ингридиентов")
    public void getAll(){
        ValidatableResponse response = client.getAllIngredients();
        ingredients = check.getSuccessfullyIngredients(response);

        Credentials credentials = Credentials.from(new User(RandomStringUtils.randomAlphabetic(5,6)+"@mail.ru",
                "1234", "name"));
        ValidatableResponse resp= userClient.createNewUser(credentials);
        accessToken = checkUser.createSuccessfullyToken(resp);
    }
    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }
    @Test
    @Step("Создание заказа с авторизацией")
    public void createOrderWithAuthorization(){
        var order = new Order(ingredients);
        ValidatableResponse response = client.createOrderAuth(accessToken, order);
        check.createSuccessfullyOrderWithAutorization(response);
    }
    @Test
    @Step("Создание заказа без авторизации")
    public void createOrderWithOutAuth(){
        var order = new Order(ingredients);
        ValidatableResponse response = client.createOrderWithOutAuth(order);
        check.createSuccessOrderWithOutAuth(response);
    }
    @Test
    @Step("Создание заказа без ингредиентов")
    public void createOrderWithOutIngred(){
        var order = new Order();
        ValidatableResponse response = client.createOrderWithOutAuth(order);
        check.createOrderWithOutIngredients(response);
    }
    @Test
    @Step("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithFailHash(){
        var order = new Order(Collections.singletonList(ingredients.get(0).replace("a", "")));
        ValidatableResponse response = client.createOrderWithOutAuth(order);
        check.createOrderWithFailHash(response);
    }
}
