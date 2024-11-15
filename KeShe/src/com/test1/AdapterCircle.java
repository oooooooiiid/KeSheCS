package com.test1;


/**
 * 圆形适配器类，将圆形适配为多边形接口。
 *
 * @author 作者名
 * @version 1.0
 * @date 2024-11-14
 */
public class AdapterCircle implements Polygon {
    private Circle circle;

    /**
     * 构造一个新的圆形适配器。
     *
     * @param circle 要适配的圆形
     */
    public AdapterCircle(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void draw() {
        circle.drawCircle();
    }

    @Override
    public boolean canForm(Vertex... vertices) {
        // 对于圆形，不需要顶点列表来形成图形，因此总是返回false
        return false;
    }

    @Override
    public double calcArea() {
        return circle.calcArea();
    }

    /**
     * 计算圆心到给定顶点的距离。
     *
     * @param vertex 给定的顶点
     * @return 圆心到顶点的距离
     */
    public double calcDistanceToCenter(Vertex vertex) {
        return circle.getCenter().calcDistance(vertex);
    }
}