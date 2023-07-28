package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import user.Credentials;
import user.User;
import user.UserAssertions;
import user.UserClient;

import java.util.Collections;
import java.util.List;

public class TestGetOrder {
    private OrderAssertions check = new OrderAssertions();
    private OrderClient client = new OrderClient();
    private UserClient userClient = new UserClient();
    private UserAssertions checkUser = new UserAssertions();
    protected String accessToken;
    protected List<String> ingredients;
    protected String orderId;
    private List<String> newOrderId;
    @Before
    @Step("Создание юзера и создание заказа от юзера")
    public void createUserAndHimOrder(){
        //создаем юзера
        Credentials credentials = Credentials.from(new User(RandomStringUtils.randomAlphabetic(5,6)+"@mail.ru",
                "1234", "name"));
        ValidatableResponse resp= userClient.createNewUser(credentials);
        accessToken = checkUser.createSuccessfullyToken(resp);

        //получаем список ингрединтов
        ValidatableResponse response = client.getAllIngredients();
        ingredients = check.getSuccessfullyIngredients(response);

        //создаем заказ на созданного юзера
        var order = new Order(Collections.singletonList(ingredients.get(0)));
        ValidatableResponse r = client.createOrderAuth(accessToken, order);
        orderId = check.getSuccessfullyOrderWithAutoriz(r);
        System.out.println(orderId);

    }

    @Test
    @Step("Получение заказа без авторизации")
    public void getOrderWithOutAuthorization(){
        ValidatableResponse response = client.getOrderWithOutAuth();
        check.getOrderWithOutAuthorization(response);
    }
    @Test
    @Step("Получение заказа с авторизацией")
    public void getOrderWithAuth(){
        ValidatableResponse response = client.getOrderWithAuth(accessToken);
        newOrderId = check.getSuccessfullyOrderWithAutorization(response);
        Assert.assertEquals(orderId, newOrderId.get(0));
    }
    @After
    public void deleteUser(){
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

}
