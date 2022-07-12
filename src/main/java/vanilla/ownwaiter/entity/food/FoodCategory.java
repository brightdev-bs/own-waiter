package vanilla.ownwaiter.entity.food;

public enum FoodCategory {

    CHICKEN("치킨"), PIZZA("피자"), CHINESE("중식"), JAPANESE("일식"), SNACK_BAR("분식"), WESTERN_FOOD("양식");

    private String category;

    FoodCategory() {
    }

    FoodCategory(String category) {
        this.category = category;
    }
}
