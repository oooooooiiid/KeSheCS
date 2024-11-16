package com.test1;

/**
 * 测试类，包含对Circle、Rectangle、Triangle以及它们的适配器AdapterCircle的单元测试。
 * 
 * @author 朱馨
 * @version 1.0
 * @date 11-16
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
//import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;

public class ClassStructureTest {
    /**
     * 测试类的继承关系。
     * 
     * <p>此方法验证Circle类是否只继承自Object类，以及AdapterCircle、Rectangle和Triangle类是否实现了Polygon接口。</p>
     */
    @Test
    public void testInheritance() {
        // Circle类除了Object之外不应有任何父类
        assertEquals(Object.class, Circle.class.getSuperclass());

        // AdapterCircle 应该实现 Polygon 接口。
        assertTrue(Polygon.class.isAssignableFrom(AdapterCircle.class));

        // Rectangle 应该实现 Polygon 接口。
        assertTrue(Polygon.class.isAssignableFrom(Rectangle.class));

        // Triangle 应该实现 Polygon 接口
        assertTrue(Polygon.class.isAssignableFrom(Triangle.class));
    }
    /**
     * 测试接口实现。
     * 
     * <p>此方法验证AdapterCircle、Rectangle和Triangle类是否实现了Polygon接口中的所有方法。</p>
     */
    @Test
    public void testInterfaceImplementation() {
        // 检查 AdapterCircle。
        Set<String> polygonMethods = getInterfaceMethods(Polygon.class);
        Set<String> adapterCircleMethods = getClassMethods(AdapterCircle.class);
        for (String method : polygonMethods) {
        	assertTrue("AdapterCircle does not implement " + method, adapterCircleMethods.contains(method));
        }

        // 检查 Rectangle
        Set<String> rectangleMethods = getClassMethods(Rectangle.class);
        for (String method : polygonMethods) {
            assertTrue("Rectangle does not implement " + method,rectangleMethods.contains(method));
        }

        // 检查 Triangle
        Set<String> triangleMethods = getClassMethods(Triangle.class);
        for (String method : polygonMethods) {
            assertTrue("Triangle does not implement " + method,triangleMethods.contains(method));
        }
    }
    /**
     * 测试方法重写。
     * 
     * <p>此方法验证AdapterCircle类是否重写了Polygon接口中的draw()、canForm()和calcArea()方法，
     * 以及Rectangle和Triangle类是否实现了这些方法。</p>
     */
    @Test
    public void testMethodOverriding() {
        // 对于 AdapterCircle，检查 draw()、canForm() 和 calcArea() 是否重写了 Polygon 接口中的方法。
        checkMethodOverriding(AdapterCircle.class, "draw", new Class<?>[]{});
        checkMethodOverriding(AdapterCircle.class, "canForm", new Class<?>[]{Vertex[].class});
        checkMethodOverriding(AdapterCircle.class, "calcArea", new Class<?>[]{});

        //对于 Rectangle，检查 draw()、canForm() 和 calcArea() 是否已实现。
        checkMethodPresence(Rectangle.class, "draw", new Class<?>[]{});
        checkMethodPresence(Rectangle.class, "canForm", new Class<?>[]{Vertex[].class});
        checkMethodPresence(Rectangle.class, "calcArea", new Class<?>[]{});

        // 对于 Triangle，检查 draw()、canForm() 和 calcArea() 是否已实现。
        checkMethodPresence(Triangle.class, "draw", new Class<?>[]{});
        checkMethodPresence(Triangle.class, "canForm", new Class<?>[]{Vertex[].class});
        checkMethodPresence(Triangle.class, "calcArea", new Class<?>[]{});
    }
    
    /**
     * 获取接口的所有方法名及其参数类型。
     * 
     * <p>此方法遍历接口的所有声明方法，并将方法名和参数类型组合成字符串集合返回。</p>
     * 
     * @param interfaceClass 要检查的接口类
     * @return 包含方法名及其参数类型的字符串集合
     */
    private Set<String> getInterfaceMethods(Class<?> interfaceClass) {
        Set<String> methods = new HashSet<>();
        for (Method method : interfaceClass.getDeclaredMethods()) {
            methods.add(method.getName() + getParameterTypes(method.getParameterTypes()));
        }
        return methods;
    }
    /**
     * 获取类的所有方法名及其参数类型，包括从父类和接口继承的方法。
     * 
     * <p>此方法遍历类的所有声明方法，以及从父类和接口继承的方法，
     * 并将方法名和参数类型组合成字符串集合返回。</p>
     * 
     * @param clazz 要检查的类
     * @return 包含方法名及其参数类型的字符串集合
     */
    private Set<String> getClassMethods(Class<?> clazz) {
        Set<String> methods = new HashSet<>();
        for (Method method : clazz.getDeclaredMethods()) {
            methods.add(method.getName() + getParameterTypes(method.getParameterTypes()));
        }
        // 包括从父类和接口继承的方法。
        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null) {
            for (Method method : superclass.getDeclaredMethods()) {
                methods.add(method.getName() + getParameterTypes(method.getParameterTypes()));
            }
            superclass = superclass.getSuperclass();
        }
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            for (Method method : interfaceClass.getDeclaredMethods()) {
                methods.add(method.getName() + getParameterTypes(method.getParameterTypes()));
            }
        }
        return methods;
    }
    /**
     * 获取方法参数类型的字符串表示。
     * 
     * <p>此方法将方法参数类型数组转换为字符串表示，例如"(Int,String)"。</p>
     * 
     * @param parameterTypes 方法参数类型数组
     * @return 方法参数类型的字符串表示
     */
    private String getParameterTypes(Class<?>[] parameterTypes) {
        StringBuilder sb = new StringBuilder("(");
        for (Class<?> type : parameterTypes) {
            sb.append(type.getSimpleName()).append(",");
        }
        if (parameterTypes.length > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove last comma
        }
        sb.append(")");
        return sb.toString();
    }
    /**
     * 检查类是否重写了指定的方法。
     * 
     * <p>此方法验证指定类是否包含重写自父类或接口的方法。</p>
     * 
     * @param clazz 要检查的类
     * @param methodName 方法名
     * @param parameterTypes 方法参数类型数组
     */
    private void checkMethodOverriding(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            assertNotNull("Method " + methodName + " not found in " + clazz.getSimpleName(),method);

            // 检查该方法是否真的重写了父类或接口的方法
            boolean isOverriding = false;
            Class<?> superclass = clazz.getSuperclass();
            while (superclass != null) {
                try {
                    superclass.getMethod(methodName, parameterTypes);
                    isOverriding = true;
                    break;
                } catch (NoSuchMethodException e) {
                    // 继续在父类中查找
                }
                superclass = superclass.getSuperclass();
            }
            for (Class<?> interfaceClass : clazz.getInterfaces()) {
                try {
                    interfaceClass.getMethod(methodName, parameterTypes);
                    isOverriding = true;
                    break;
                } catch (NoSuchMethodException e) {
                    // 继续在接口中查找
                }
            }
            assertTrue("Method " + methodName + " in " + clazz.getSimpleName() + " is not overriding any method",isOverriding);
        } catch (NoSuchMethodException e) {
            fail("Method " + methodName + " not found in " + clazz.getSimpleName());
        }
    }
    /**
     * 检查类中是否存在指定的方法。
     * 
     * <p>此方法验证指定类中是否存在具有给定名称和参数类型的方法。</p>
     * 
     * @param clazz 要检查的类
     * @param methodName 方法名
     * @param parameterTypes 方法参数类型数组
     */
    private void checkMethodPresence(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            assertNotNull( "Method " + methodName + " not found in " + clazz.getSimpleName(),method);
        } catch (NoSuchMethodException e) {
            fail("Method " + methodName + " not found in " + clazz.getSimpleName());
        }
    }
}
