package com.test1;

/**
 * 多边形接口，定义了绘制多边形、判断顶点是否能构成多边形以及计算多边形面积的方法。
 *
 * @author 朱馨
 * @version 1.0
 * @date 2024-11-14
 */
public interface Polygon {
    /**
     * 绘制多边形。
     */
    void draw();

    /**
     * 判断给定的顶点是否能构成一个多边形。
     *
     * @param vertices 顶点数组
     * @return 如果能构成多边形则返回true，否则返回false
     */
    boolean canForm(Vertex... vertices);

    /**
     * 计算多边形的面积。
     *
     * @return 多边形的面积
     */
    double calcArea();
}