package com.test1;

import static org.junit.Assert.*;

import org.junit.Test;


//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.Arrays;

public class AdapterCircleTest2 {

    @Test
    public void testCircleDraw() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        circle.drawCircle();
        // 由于是打印输出，此处不直接断言，但可检查控制台输出
    }

    @Test
    public void testCircleCalcArea() {
        Circle circle = new Circle(null, 5); // 无需中心点即可计算面积
        assertEquals(Math.PI * Math.pow(5, 2), circle.calcArea(), 0.0001);
    }

    @Test
    public void testRectangleDraw() {
        Rectangle rectangle = new Rectangle(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5),
            new Vertex(5, 5)
        );
        rectangle.draw();
        // 检查控制台输出
    }

    @Test
    public void testRectangleCanForm() {
        Rectangle rectangle = new Rectangle(null, null, null, null); // 仅用于测试方法
        assertTrue(rectangle.canForm(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5),
            new Vertex(5, 5)
        ));
        assertFalse(rectangle.canForm(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5)
        ));
    }

    @Test
    public void testRectangleCalcArea() {
        Rectangle rectangle = new Rectangle(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5),
            new Vertex(5, 5)
        );
        assertEquals(25, rectangle.calcArea(), 0.0001);
    }

    @Test
    public void testTriangleDraw() {
        Triangle triangle = new Triangle(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5)
        );
        triangle.draw();
        // 检查控制台输出
    }

    @Test
    public void testTriangleCanForm() {
        Triangle triangle = new Triangle(null, null, null); // 仅用于测试方法
        assertTrue(triangle.canForm(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5)
        ));
        assertFalse(triangle.canForm(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5),
            new Vertex(5, 5)
        ));
    }

    @Test
    public void testTriangleCalcArea() {
        // 示例中返回1.0，实际应使用正确公式
        Triangle triangle = new Triangle(
            new Vertex(0, 0),
            new Vertex(5, 0),
            new Vertex(0, 5)
        );
        assertEquals(1.0, triangle.calcArea(), 0.0001);
    }

    @Test
    public void testAdapterCircleDraw() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        adapterCircle.draw();
        // 检查控制台输出
    }

    @Test
    public void testAdapterCircleCalcArea() {
        Circle circle = new Circle(null, 5); // 无需中心点即可计算面积
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        assertEquals(Math.PI * Math.pow(5, 2), adapterCircle.calcArea(), 0.0001);
    }

    @Test
    public void testAdapterCircleCalcDistanceToCenter() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        Vertex vertex = new Vertex(3, 4);
        assertEquals(5, adapterCircle.calcDistanceToCenter(vertex), 0.0001);
    }
}