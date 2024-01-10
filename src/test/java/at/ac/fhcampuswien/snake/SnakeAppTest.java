package at.ac.fhcampuswien.snake;

class SnakeAppTest {

// example code from CalculatorTest
/*    static Calculator calc;

    @BeforeAll
    static void setup() {
        calc = new Calculator();
    }

    @Test
    void givenTwoNumbers_whenAddingThem_thenReturnSum() {
        // Setup
        int expectedResult = 2;

        // Given
        int numberOne = 1;
        int numberTwo = 1;

        // When I add them
        int actual = calc.add(numberOne, numberTwo);

        // Then return sum
        assertEquals(expectedResult, actual);
    }

    @Test
    @DisplayName("Subtract: 2 - 1 = 1")
    void subtract() {
        // Arrange
        int expectedResult = 1;
        int numberOne = 2;
        int numberTwo = 1;

        // Act
        int actualResult = calc.subtract(numberOne, numberTwo);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Multiply: 3 * 3 = 9")
    void multiply() {
        // Arrange
        int expectedResult = 9;
        int a = 3;
        int b = 3;

        // Act
        int actualResult = calc.multiply(a, b);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Divide: 6 / 3 = 2")
    void divide() {
        // Arrange
        int expectedResult = 2;
        // Act & Assert
        assertEquals(expectedResult, calc.divide(6, 3));
    }

    @Test
    @DisplayName("Divide by zero")
    void divideByZero(){
        // Arrange
        String expectedMessage = "/ by zero";

        // Act & Assert
        Exception ex = assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("Larger Than: 4 > 2 = True and 1 > 5 = False")
    void largerThan() {
        // Act & Assert
        assertTrue(calc.largerThan(4, 2));
        assertFalse(calc.largerThan(1, 5));
    }

    @ParameterizedTest
    @CsvSource({"1,1,2", "1,2,3", "5,10,15"})
    void add(int a, int b, int sum) {
        // Arrange & Act
        int actualResult = calc.add(a, b);

        // Assert
        assertEquals(sum, actualResult);
    }*/
}