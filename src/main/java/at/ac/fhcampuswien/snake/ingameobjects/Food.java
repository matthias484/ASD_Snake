package at.ac.fhcampuswien.snake.ingameobjects;

import at.ac.fhcampuswien.snake.util.Constants;
import at.ac.fhcampuswien.snake.util.StateManager;

import java.util.Objects;

public class Food {

    private final Position position;
    private String foodType;
    private boolean isSpecialFood;
    private int scoreValue;
    private int specialFoodTimeToLive;

    private static final int REGULAR_SCORE_VALUE = 1;
    private static final int SPECIAL_SCORE_VALUE = 3;

    private static final String[] REGULAR_FOOD_TYPES = new String[]{"strawberry.png", "watermelon.png", "bell_pepper.png",
            "kiwi.png", "orange.png", "tomato.png", "cherry.png", "green_grapes.png", "coconut.png", "melon.png",
            "peach.png", "blue_grapes.png", "blueberries.png", "banana.png", "chili_pepper.png"};

    private static final String[] SPECIAL_FOOD_TYPES = new String[]{"special_food_1.png", "special_food_2.png",
            "special_food_3.png", "special_food_4.png", "special_food_5.png", "special_food_6.png"};




    public Food(Snake snake, Wall wall, Food currentlyExistingRegularFood,
                boolean isSpecialFood, String previousFoodType) {
        int scoreValueMultiplier = calculateScoreValueMultiplier();
        generateFoodType(isSpecialFood, previousFoodType, scoreValueMultiplier);
        this.position = determineFoodPosition(snake, wall, currentlyExistingRegularFood, isSpecialFood);
    }

    private int calculateScoreValueMultiplier() {
        return switch (StateManager.getDifficulty()) {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };
    }

    private void generateFoodType(boolean isSpecialFood, String previousFoodType, int scoreValueMultiplier) {
        if (isSpecialFood) {
            this.isSpecialFood = true;
            this.scoreValue = SPECIAL_SCORE_VALUE * scoreValueMultiplier;
            this.specialFoodTimeToLive = (int) (18 + (Math.random() * 18));
            do {
                int foodTypeNumber = (int) (Math.random() * SPECIAL_FOOD_TYPES.length);
                this.foodType = SPECIAL_FOOD_TYPES[foodTypeNumber];
            } while (Objects.equals(this.foodType, previousFoodType));
        } else {
            this.scoreValue = REGULAR_SCORE_VALUE * scoreValueMultiplier;
            this.specialFoodTimeToLive = -1;
            do {
                int foodTypeNumber = (int) (Math.random() * REGULAR_FOOD_TYPES.length);
                this.foodType = REGULAR_FOOD_TYPES[foodTypeNumber];
            } while (Objects.equals(this.foodType, previousFoodType));
        }
    }

    private Position determineFoodPosition(Snake snake, Wall wall, Food currentlyExistingRegularFood, boolean isSpecialFood) {
        boolean isTargetFieldFree;
        int foodXCoord;
        int foodYCoord;
        int segmentNumberX = Constants.NUMBER_OF_ROWS_AND_COLS - 2;
        int segmentNumberY = Constants.NUMBER_OF_ROWS_AND_COLS - 3;

        do {
            isTargetFieldFree = true;
            foodXCoord = (int) (Math.random() * segmentNumberX) + 1;
            foodYCoord = (int) (Math.random() * segmentNumberY) + 1;

            foodXCoord *= Constants.OBJECT_SIZE_MEDIUM;
            foodYCoord *= Constants.OBJECT_SIZE_MEDIUM;

            // Check if calculated Position is inhibited by the snake
            int i = 0;
            do {
                if (snake.getSegments().get(i).x() == foodXCoord &&
                        snake.getSegments().get(i).y() == foodYCoord) isTargetFieldFree = false;
                i++;
            } while (isTargetFieldFree && i < snake.getSegments().size());

            // Check if calculated Position is inhibited by the wall
            if (wall != null && isTargetFieldFree) {
                int j = 0;
                do {
                    if (wall.getSegments().get(j).x() == foodXCoord &&
                            wall.getSegments().get(j).y() == foodYCoord) isTargetFieldFree = false;
                    j++;
                } while (isTargetFieldFree && j < wall.getSegments().size());
            }

            // Check if currently existing regular Food is on desired Position
            if (isSpecialFood && currentlyExistingRegularFood != null && isTargetFieldFree) {
                Position existingFoodPos = currentlyExistingRegularFood.getLocation();
                if (existingFoodPos.x() == foodXCoord && existingFoodPos.y() == foodYCoord) {
                    isTargetFieldFree = false;
                }
            }

        } while (!isTargetFieldFree);

        return new Position(foodXCoord, foodYCoord);
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public Position getLocation() {
        return position;
    }

    public String getFoodType() {
        return foodType;
    }

    public boolean isSpecialFood() {
        return isSpecialFood;
    }

    public int getSpecialFoodTimeToLive() {
        return specialFoodTimeToLive;
    }

    public void decreaseSpecialFoodTimeToLive() {
        specialFoodTimeToLive--;
    }
}
