package main.java.oop;

import java.awt.*;

public class Point {
    private int x;
    private int y;

    // region Конструкторы

    public Point() {
        this(0, 0);
    }

    public Point(Point p) {
        setLocation(p.x, p.y);
    }

    public Point(int x, int y) {
        setLocation(x, y);
    }

    // endregion

    public final void draw(Graphics g) {
        g.fillOval(x, y, 3, 3);
        g.drawString(toString(), x, y);
    }

    /**
     * Расстояние до центра координат
     * @return расстояние
     */
    public double distanceFromOrigin() {
        return distance(new Point());
    }

    public double distance(Point p) {
        int dx = x - p.x;
        int dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Смещение точки по x и y
     * @param dx смещение по x
     * @param dy смещение по y
     */
    public void translate(int dx, int dy) {
        setLocation(x + dx, y + dy);
    }

    // region геттеры и сеттеры

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    // endregion

    // region Методы Object

    public final boolean equals(Object o) {
        if (o instanceof Point) {
            Point other = (Point) o;
            return x == other.x && y == other.y;
        } else {
            return false;
        }
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // endregion
}
