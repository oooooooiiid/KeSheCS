package com.test1;
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

    private Set<String> getInterfaceMethods(Class<?> interfaceClass) {
        Set<String> methods = new HashSet<>();
        for (Method method : interfaceClass.getDeclaredMethods()) {
            methods.add(method.getName() + getParameterTypes(method.getParameterTypes()));
        }
        return methods;
    }

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

    private void checkMethodPresence(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            assertNotNull( "Method " + methodName + " not found in " + clazz.getSimpleName(),method);
        } catch (NoSuchMethodException e) {
            fail("Method " + methodName + " not found in " + clazz.getSimpleName());
        }
    }
}
