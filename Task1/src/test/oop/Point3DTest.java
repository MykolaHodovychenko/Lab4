package oop;

import main.java.oop.Point3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Тестирование класса Point3D")
public class Point3DTest {

    private String getExpectedString(int x, int y, int z) {
        return "(" + x + "," + y + "," + z + ")";
    }

    @DisplayName("Тестирование конструктора без параметров")
    @Order(1)
    @Test
    public void testConstructorNoArgs() {
        Point3D point3D = new Point3D();
        Assertions.assertEquals("(0,0,0)", point3D.toString(), "Ожидаемые атрибуты объекта не равны фактическому");
    }

    @DisplayName("Тестирования конструктора с параметрами")
    @ParameterizedTest(name = "new Point3D({0}, {1}, {2});")
    @MethodSource(value = "twoArgsConstructorTestArgumentsProvider")
    @Order(2)
    public void testConstructorTwoArgs(int x, int y, int z, int nx, int ny, int nz, String errorMessage) {
        Point3D point3D = new Point3D(x, y, z);
        String expected = getExpectedString(nx, ny, nz);
        Assertions.assertEquals(expected, point3D.toString(), errorMessage);
    }

    @DisplayName("Тестирования метода toString()")
    @ParameterizedTest(name = "({0},{1},{2}) --> toString();")
    @MethodSource(value = "toStringTestArgumentsProvider")
    @Order(3)
    public void testToString(int x, int y, int z, String expected, String errorMessage) {
        Point3D point3D = new Point3D(x, y, z);
        Assertions.assertEquals(expected, point3D.toString(), errorMessage);
    }

    @DisplayName("Тестирования метода setLocation(int x, int y);")
    @ParameterizedTest(name = "({0},{1},{2}) --> setLocation({3}, {4});")
    @MethodSource(value = "locationTwoArgsTestArgumentsProvider")
    @Order(4)
    public void testLocationTwoArgs(int px, int py, int pz, int x, int y, int nx, int ny, int nz, String errorMessage) {
        Point3D point3D = new Point3D(px, py, pz);
        String expected = getExpectedString(nx, ny, nz);
        point3D.setLocation(x, y);
        Assertions.assertEquals(expected, point3D.toString(), errorMessage);
    }

    @DisplayName("Тестирования метода setLocation(int x, int y, int z);")
    @ParameterizedTest(name = "({0},{1},{2}) --> setLocation({3}, {4}, {5});")
    @MethodSource(value = "locationThreeArgsTestArgumentsProvider")
    @Order(5)
    public void testLocationTwoArgs(int px, int py, int pz, int x, int y, int z, int nx, int ny, int nz, String errorMessage) {
        Point3D point3D = new Point3D(px, py, pz);
        String expected = getExpectedString(nx, ny, nz);
        point3D.setLocation(x, y, z);
        Assertions.assertEquals(expected, point3D.toString(), errorMessage);
    }

    @DisplayName("Тестирование геттеров")
    @ParameterizedTest(name = "({0},{1},{2}) --> {4}({3});")
    @MethodSource(value = "gettersTestArgumentsProvider")
    @Order(6)
    public void testGetters(int px, int py, int pz, int expected, String method, String errorMessage) {
        Point3D point3D = new Point3D(px, py, pz);

        int actual = 0;
        switch (method) {
            case "getX":
                actual = point3D.getX();
                break;
            case "getY":
                actual = point3D.getY();
                break;
            case "getZ":
                actual = point3D.getZ();
        }

        Assertions.assertEquals(expected, actual, errorMessage);
    }

    @DisplayName("Тестирование метода distanceFromOrigin()")
    @ParameterizedTest(name = "({0},{1},{2}) --> distanceFromOrigin();")
    @MethodSource(value = "distanceFromOriginTestArgumentsProvider")
    @Order(7)
    public void testDistanceFromOrigin(int px, int py, int pz, double expected, String errorMessage) {
        Point3D point3D = new Point3D(px, py, pz);
        Assertions.assertEquals(expected, point3D.distanceFromOrigin(), 0.000001, errorMessage);
    }

    // region Провайдеры

    private static Stream<Arguments> twoArgsConstructorTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0, 0, 0, 0, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(1, 1, 1, 1, 1, 1, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(-1, -1, -1, -1, -1, -1, "Ожидаемые координаты точки не равны фактическим")
        );
    }

    private static Stream<Arguments> toStringTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 1, -1, "(0,1,-1)", "Ожидаемая строка не равна фактической"),
                Arguments.of(4, 8, 16, "(4,8,16)", "Ожидаемая строка не равна фактической")
        );
    }

    private static Stream<Arguments> locationTwoArgsTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0, 1, -1, 1, -1, 0, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(1, 1, 1, 0, -1, 0, -1, 0, "Ожидаемые координаты точки не равны фактическим")
        );
    }

    private static Stream<Arguments> locationThreeArgsTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0, 1, 0, 0, 1, 0, 0, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(1, 0, 0, 0, -1, 0, 0, -1, 0, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(0, -1, 0, 0, 0, 3, 0, 0, 3, "Ожидаемые координаты точки не равны фактическим"),
                Arguments.of(0, 0, 3, 0, 0, 0, 0, 0, 0, "Ожидаемые координаты точки не равны фактическим")
        );
    }

    private static Stream<Arguments> gettersTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(-3, 6, 3, -3, "getX", "Ожидаемая координата X не равна фактической"),
                Arguments.of(-3, 6, 3, 6, "getY", "Ожидаемая координата Y не равна фактической"),
                Arguments.of(-3, 6, 3, 3, "getZ", "Ожидаемая координата Z не равна фактической")
        );
    }

    private static Stream<Arguments> distanceFromOriginTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0, 0.0, "Ожидаемое расстояние не равно фактическому"),
                Arguments.of(1, 0, 0, 1.0, "Ожидаемое расстояние не равно фактическому"),
                Arguments.of(1, -1, 0, 1.4142135623730951, "Ожидаемое расстояние не равно фактическому"),
                Arguments.of(-2, 2, 1, 3.0, "Ожидаемое расстояние не равно фактическому"),
                Arguments.of(3, 3, 3, 5.196152422706632, "Ожидаемое расстояние не равно фактическому")
        );
    }

    // endregion
}
