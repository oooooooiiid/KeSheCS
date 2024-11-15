package com.test1;

/**
 * 圆形类，表示一个圆。
 *
 * @author 朱馨
 * @version 1.0
 * @date 2024-11-14
 */
public class Circle {
    private Vertex center;
    private double radius;

    /**
     * 构造一个新的圆。
     *
     * @param center 圆心
     * @param radius 半径
     */
    public Circle(Vertex center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * 获取圆心。
     *
     * @return 圆心
     */
    public Vertex getCenter() {
        return center;
    }

    /**
     * 绘制圆形。
     */
    public void drawCircle() {
        System.out.println("Drawing a Circle with center at (" + center.getX() + ", " + center.getY() + ") and radius " + radius);
    }

    /**
     * 计算圆的面积。
     *
     * @return 圆的面积
     */
    public double calcArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}