package com.test1;

/**
 * 顶点类，表示二维空间中的一个点。
 *
 * @author 作者名
 * @version 1.0
 * @date 2024-11-14
 */
public class Vertex {
    private double x;
    private double y;

    /**
     * 构造一个新的顶点。
     *
     * @param x X坐标
     * @param y Y坐标
     */
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 获取X坐标。
     *
     * @return X坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获取Y坐标。
     *
     * @return Y坐标
     */
    public double getY() {
        return y;
    }

    /**
     * 计算当前顶点与另一个顶点之间的距离。
     *
     * @param other 另一个顶点
     * @return 两个顶点之间的距离
     */
    public double calcDistance(Vertex other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}
















