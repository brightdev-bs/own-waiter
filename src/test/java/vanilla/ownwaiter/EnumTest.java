package vanilla.ownwaiter;

import org.junit.jupiter.api.Test;
import vanilla.ownwaiter.entity.food.FoodCategory;

public class EnumTest {

    @Test
    void ordianl() {
        for (FoodCategory value : FoodCategory.values()) {
            System.out.println(value);
        }

        System.out.println(FoodCategory.valueOf("치킨"));
    }
}
