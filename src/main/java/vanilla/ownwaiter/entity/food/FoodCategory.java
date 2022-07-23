package vanilla.ownwaiter.entity.food;

import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Unknown;

import java.util.Arrays;
import java.util.function.Function;
@Getter
public enum FoodCategory {

    CHICKEN("치킨"), PIZZA("피자"), CHINESE("중식"), JAPANESE("일식"), SNACK_BAR("분식"), WESTERN_FOOD("양식");

    private String category;

    FoodCategory() {
    }

    FoodCategory(String category) {
        this.category = category;
    }
}
