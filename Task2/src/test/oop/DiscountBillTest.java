package oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class DiscountBillTest {

    @DisplayName("Тестирование конструктора с параметрами")
    @ParameterizedTest(name = "new DiscountBill(clerk, {0});")
    @MethodSource(value = "twoArgsConstructorTestArgumentsProvider")
    @Order(1)
    public void testConstructorTwoArgs(Employee clerk, boolean isRegular, String expectedClerk, String errorMessage) {
        DiscountBill bill = new DiscountBill(clerk, isRegular);
        Assertions.assertEquals(expectedClerk, bill.getClerk().getName(), errorMessage);
    }

    @DisplayName("Тестирование метода getTotal();")
    @ParameterizedTest(name = " {0} --> getTotal();")
    @MethodSource(value = "getTotalTestArgumentsProvider")
    @Order(2)
    public void testGetTotal(boolean isRegular, List<Item> list, double expectedTotal, String errorMessage) {
        DiscountBill bill = new DiscountBill(new Employee(""), isRegular);
        list.forEach(bill::add);
        Assertions.assertEquals(expectedTotal, bill.getTotal(), errorMessage);
    }

    @DisplayName("Тестирование метода getDiscountCount();")
    @ParameterizedTest(name = "({0}, {2}) --> getDiscountCount();")
    @MethodSource(value = "getDiscountCountTestArgumentsProvider")
    @Order(3)
    public void testGetDiscountCount(boolean isRegular, List<Item> list, int expectedCount, String errorMessage) {
        DiscountBill bill = new DiscountBill(new Employee(""), isRegular);
        list.forEach(bill::add);
        Assertions.assertEquals(expectedCount, bill.getDiscountCount(), errorMessage);
    }

    @DisplayName("Тестирование метода getDiscountAmount();")
    @ParameterizedTest(name = "({0}, {2}) --> getDiscountAmount();")
    @MethodSource(value = "getDiscountAmountTestArgumentsProvider")
    @Order(4)
    public void testGetDiscountAmount(boolean isRegular, List<Item> list, double expectedAmount, String errorMessage) {
        DiscountBill bill = new DiscountBill(new Employee(""), isRegular);
        list.forEach(bill::add);
        Assertions.assertEquals(expectedAmount, bill.getDiscountAmount(), errorMessage);
    }

    @DisplayName("Тестирование метода getDiscountPercent();")
    @ParameterizedTest(name = "({0}, {2}) --> getDiscountPercent();")
    @MethodSource(value = "getDiscountPercentTestArgumentsProvider")
    @Order(4)
    public void testGetDiscountPercent(boolean isRegular, List<Item> list, double expectedPercent, String errorMessage) {
        DiscountBill bill = new DiscountBill(new Employee(""), isRegular);
        list.forEach(bill::add);
        Assertions.assertEquals(expectedPercent, bill.getDiscountPercent(), 0.000001, errorMessage);
    }

    // region Провайдеры

    private static Stream<Arguments> getDiscountPercentTestArgumentsProvider() {

        List<Item> items1 = List.of(
                new Item("товар1", 3.00, 0.75)
        );
        double total1 = items1.stream().mapToDouble(Item::getPrice).sum();
        double amount1 = items1.stream().mapToDouble(Item::getDiscount).sum();

        List<Item> items2 = List.of(
                new Item("товар1", 4.18, 0.0)
        );
        double total2 = items2.stream().mapToDouble(Item::getPrice).sum();
        double amount2 = items2.stream().mapToDouble(Item::getDiscount).sum();

        List<Item> items3 = List.of(
                new Item("товар1", 4.18, 0.00),
                new Item("товар2", 2.50, 0.15),
                new Item("товар3", 3.00, 0.75),
                new Item("товар4", 4.25, 0.25),
                new Item("товар5", 6.50, 2.25)
        );
        double total3 = items3.stream().mapToDouble(Item::getPrice).sum();
        double amount3 = items3.stream().mapToDouble(Item::getDiscount).sum();

        return Stream.of(
                Arguments.of(false, items1, 0, "Ожидаемый процент скидки не равен фактическому"),
                Arguments.of(true, items1, amount1 / total1 * 100, "Ожидаемый процент скидки не равен фактическому"),
                Arguments.of(false, items2, 0, "Ожидаемый процент скидки не равен фактическому"),
                Arguments.of(true, items2, amount2 / total2 * 100, "Ожидаемый процент скидки не равен фактическому"),
                Arguments.of(false, items3, 0, "Ожидаемый процент скидки не равен фактическому"),
                Arguments.of(true, items3, amount3 / total3 * 100, "Ожидаемый процент скидки не равен фактическому")
        );
    }

    private static Stream<Arguments> getDiscountAmountTestArgumentsProvider() {

        List<Item> items1 = List.of(
                new Item("товар1", 1.02, 0.12)
        );
        double amount1 = items1.stream().mapToDouble(Item::getDiscount).sum();

        List<Item> items2 = List.of(
                new Item("товар1", 1.50, 0.0)
        );
        double amount2 = items2.stream().mapToDouble(Item::getDiscount).sum();

        List<Item> items3 = List.of(
                new Item("товар1", 1.02, 0.12),
                new Item("товар2", 3.25, 0.15),
                new Item("товар3", 67.90, 18.20),
                new Item("товар4", 1.50, 0.00)
        );
        double amount3 = items3.stream().mapToDouble(Item::getDiscount).sum();

        return Stream.of(
                Arguments.of(false, items1, 0, "Ожидаемая сумма скидки не равна фактической"),
                Arguments.of(true, items1, amount1, "Ожидаемая сумма скидки не равна фактической"),
                Arguments.of(false, items2, 0, "Ожидаемая сумма скидки не равна фактической"),
                Arguments.of(true, items2, amount2, "Ожидаемая сумма скидки не равна фактической"),
                Arguments.of(false, items3, 0, "Ожидаемая сумма скидки не равна фактической"),
                Arguments.of(true, items3, amount3, "Ожидаемая сумма скидки не равна фактической")
        );
    }

    private static Stream<Arguments> getDiscountCountTestArgumentsProvider() {

        List<Item> items1 = List.of(
                new Item("товар1", 125.00, 35.50)
        );
        int count1 = (int) items1.stream().filter(e -> e.getDiscount() > 0).count();

        List<Item> items2 = List.of(
                new Item("товар1", 75.00, 0.0)
        );
        int count2 = (int) items2.stream().filter(e -> e.getDiscount() > 0).count();

        List<Item> items3 = List.of(
                new Item("товар1", 3.00, 0.15),
                new Item("товар2", 1.50, 0.00),
                new Item("товар3", 2.25, 0.35),
                new Item("товар4", 75.00, 0.00),
                new Item("товар5", 1.30, 0.16),
                new Item("товар6", 125.00, 35.50)
        );
        int count3 = (int) items3.stream().filter(e -> e.getDiscount() > 0).count();

        return Stream.of(
                Arguments.of(false, items1, 0, "Ожидаемое количество товаров со скидкой не равно фактическому"),
                Arguments.of(true, items1, count1, "Ожидаемое количество товаров со скидкой не равно фактическому"),
                Arguments.of(false, items2, 0, "Ожидаемое количество товаров со скидкой не равно фактическому"),
                Arguments.of(true, items2, count2, "Ожидаемое количество товаров со скидкой не равно фактическому"),
                Arguments.of(false, items3, 0, "Ожидаемое количество товаров со скидкой не равно фактическому"),
                Arguments.of(true, items3, count3, "Ожидаемое количество товаров со скидкой не равно фактическому")
        );
    }

    private static Stream<Arguments> twoArgsConstructorTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Employee("clerk-1"), false, "clerk-1", "Ожидаемые атрибуты объекта не равны фактическим"),
                Arguments.of(new Employee("clerk-2"), true, "clerk-2", "Ожидаемые атрибуты объекта не равны фактическим")
        );
    }

    private static Stream<Arguments> getTotalTestArgumentsProvider() {

        List<Item> items1 = List.of(
                new Item("товар1", 1.35, 0.25)
        );
        double sum1 = items1.stream().mapToDouble(Item::getPrice).sum();
        double disc1 = items1.stream().mapToDouble(e -> e.getPrice() - e.getDiscount()).sum();

        List<Item> items2 = List.of(
                new Item("товар1", 0.99, 0.0)
        );
        double sum2 = items2.stream().mapToDouble(Item::getPrice).sum();
        double disc2 = items2.stream().mapToDouble(e -> e.getPrice() - e.getDiscount()).sum();

        List<Item> items3 = List.of(
                new Item("товар1", 1.35, 0.25),
                new Item("товар2", 3.25, 0.50),
                new Item("товар3", 0.30, 0.05),
                new Item("товар4", 0.30, 0.05),
                new Item("товар5", 0.99, 0.0)
        );
        double sum3 = items3.stream().mapToDouble(Item::getPrice).sum();
        double disc3 = items3.stream().mapToDouble(e -> e.getPrice() - e.getDiscount()).sum();

        return Stream.of(
                Arguments.of(false, items1, sum1, "Ожидаемая сумма чека не равна фактической"),
                Arguments.of(true, items1, disc1, "Ожидаемая сумма чека не равна фактической"),
                Arguments.of(false, items2, sum2, "Ожидаемая сумма чека не равна фактической"),
                Arguments.of(true, items2, disc2, "Ожидаемая сумма чека не равна фактической"),
                Arguments.of(false, items3, sum3, "Ожидаемая сумма чека не равна фактической"),
                Arguments.of(true, items3, disc3, "Ожидаемая сумма чека не равна фактической")
        );
    }

    // endregion
}
