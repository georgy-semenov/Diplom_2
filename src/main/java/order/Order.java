package order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<String> ingredients;
    public Order() {
        ingredients = new ArrayList<>();
    }
    public Order(List<String> ingredients){
        this.ingredients = ingredients;
    }

}
