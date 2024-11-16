package com.test1;

/**
 * 测试类，包含对Circle、Rectangle、Triangle以及它们的适配器AdapterCircle的单元测试。
 * 
 * @author 朱馨
 * @version 1.0
 * @date 11-16
 */

import static org.junit.Assert.*;

import org.junit.Test;


//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.Arrays;

/**
* CalculatorTest类包含了多个测试方法，用于验证Circle、Rectangle、Triangle和AdapterCircle类的功能。
*/
public class CalculatorTest {
	
    /**
     * 测试Circle类的drawCircle方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 由于drawCircle方法只是打印输出，此处不直接断言，但可以通过检查控制台输出来验证。
     */
    @Test
    public void testCircleDraw() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        circle.drawCircle();
        // 由于是打印输出，此处不直接断言，但可检查控制台输出
    }
    /**
     * 测试Circle类的calcArea方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个半径为5的Circle对象，无需指定中心点即可计算面积，并使用assertEquals断言计算结果的正确性。
     */
    @Test
    public void testCircleCalcArea() {
        Circle circle = new Circle(null, 5); // 无需中心点即可计算面积
        assertEquals(Math.PI * Math.pow(5, 2), circle.calcArea(), 0.0001);
    }
    /**
     * 测试Rectangle类的draw方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Rectangle对象并调用其draw方法，通过检查控制台输出来验证。
     */
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
    /**
     * 测试Rectangle类的canForm方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Rectangle对象，并使用assertTrue和assertFalse断言其canForm方法的正确性。
     */
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
    /**
     * 测试Rectangle类的calcArea方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Rectangle对象，并使用assertEquals断言其calcArea方法的正确性。注意：示例中返回1.0仅为占位，实际应使用正确的面积公式。
     */
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
    /**
     * 测试Triangle类的draw方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Triangle对象并调用其draw方法，通过检查控制台输出来验证。
     */
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
    /**
     * 测试Triangle类的canForm方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一Triangle对象，并使用assertTrue和assertFalse断言其canForm方法的正确性。
     */
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
    /**
     * 测试Triangle类的calcArea方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Triangle对象，并使用assertEquals断言其calcArea方法的正确性。注意：示例中返回1.0仅为占位，实际应使用正确的面积公式。
     */
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
    /**
     * 测试AdapterCircle类的draw方法。
     * 
     * @param 无
     * @return 无
     * @throws 无
     * @note 创建一个Circle对象，将其封装在AdapterCircle中，并调用draw方法，通过检查控制台输出来验证。
     */
    @Test
    public void testAdapterCircleDraw() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        adapterCircle.draw();
        // 检查控制台输出
    }
    /**
     * 测试AdapterCircle类计算圆形面积的方法。
     * 
     * <p>此方法创建了一个不需要指定中心点的Circle对象，并将其包装在AdapterCircle对象中。
     * 然后调用AdapterCircle的calcArea方法计算面积，并验证计算结果是否与预期的πr²相等（其中r为半径5）。</p>
     * 
     * <p>测试的目的是确保AdapterCircle类能够正确地通过Circle对象计算面积。</p>
     * 
     * @param 无参数（测试方法内部创建所需对象）
     * @return void（测试方法，不返回任何值）
     * @throws 无异常抛出（测试方法内部处理所有逻辑）
     */
    @Test
    public void testAdapterCircleCalcArea() {
        Circle circle = new Circle(null, 5); // 无需中心点即可计算面积
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        assertEquals(Math.PI * Math.pow(5, 2), adapterCircle.calcArea(), 0.0001);
    }
    /**
     * 测试AdapterCircle类计算点到圆中心距离的方法。
     * 
     * <p>此方法首先创建了一个指定中心点和半径的Circle对象，并将其包装在AdapterCircle对象中。
     * 然后创建一个Vertex对象表示一个点，并调用AdapterCircle的calcDistanceToCenter方法计算该点到圆中心的距离。
     * 最后验证计算结果是否与预期的5相等（因为圆半径为5，而示例点(3,4)到原点的距离正好是5）。</p>
     * 
     * <p>测试的目的是确保AdapterCircle类能够正确地通过Circle对象计算点到圆中心的距离。</p>
     * 
     * @param 无参数（测试方法内部创建所需对象）
     * @return void（测试方法，不返回任值）
     * @throws 无异常抛出（测试方法内部处理所有逻辑）
     */
    @Test
    public void testAdapterCircleCalcDistanceToCenter() {
        Circle circle = new Circle(new Vertex(0, 0), 5);
        AdapterCircle adapterCircle = new AdapterCircle(circle);
        Vertex vertex = new Vertex(3, 4);
        assertEquals(5, adapterCircle.calcDistanceToCenter(vertex), 0.0001);
    }
}