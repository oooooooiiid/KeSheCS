package com.test1;

import java.util.Arrays;

/**
 * 矩形类，表示一个矩形。
 *
 * @author 朱馨
 * @version 1.0
 * @date 2024-11-14
 */
public class Rectangle implements Polygon {
    private Vertex topLeft;
    private Vertex topRight;
    private Vertex bottomLeft;
    private Vertex bottomRight;

    /**
     * 构造一个新的矩形。
     *
     * @param topLeft 左上角顶点
     * @param topRight 右上角顶点
     * @param bottomLeft 左下角顶点
     * @param bottomRight 右下角顶点
     */
    public Rectangle(Vertex topLeft, Vertex topRight, Vertex bottomLeft, Vertex bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle with vertices: " + Arrays.toString(new Vertex[]{topLeft, topRight, bottomLeft, bottomRight}));
    }

    @Override
    public boolean canForm(Vertex... vertices) {
        // 简化示例，仅检查顶点数量，实际应实现完整的逻辑
        return vertices.length == 4;
    }

    @Override
    public double calcArea() {
        double width = topRight.calcDistance(topLeft);
        double height = bottomRight.calcDistance(topRight);
        return width * height;
    }
}