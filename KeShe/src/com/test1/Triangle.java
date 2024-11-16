package com.test1;

import java.util.Arrays;

/**
 * 三角形类，表示一个三角形。
 *
 * @author 朱馨
 * @version 1.0
 * @date 2024-11-14
 */
public class Triangle implements Polygon {
    private Vertex[] vertices;

    /**
     * 构造一个新的三角形。
     *
     * @param vertices 三角形的三个顶点
     * @throws IllegalArgumentException 如果顶点数量不是3个
     */
    public Triangle(Vertex... vertices) {
        if (vertices.length != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 vertices.");
        }
        this.vertices = vertices;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle with vertices: " + Arrays.toString(vertices));
    }

    @Override
    public boolean canForm(Vertex... vertices) {
        // 简化示例，仅检查顶点数量，实际应实现完整的逻辑
        return vertices.length == 3;
    }

    @Override
    public double calcArea() {
        // 简化示例，实际应使用海伦公式或其他方法计算面积
        return 1.0; // 示例值
    }
}