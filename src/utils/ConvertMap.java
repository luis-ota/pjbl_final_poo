package utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConvertMap {
    public static <T> T mapToObject(Map<String, String> map, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            try {
                Method setter = clazz.getMethod(setterName, String.class);
                setter.invoke(obj, fieldValue);
            } catch (NoSuchMethodException e) {
                System.out.println("No such setter method: " + setterName);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("Illegal access or argument to method: " + setterName);
            }
        }

        return obj;
    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0 &&
                        !method.getName().equals("getClass") &&
                        !method.getName().equals("getTipo") &&
                        !method.getName().equals("getNomeCompleto")) {
                    String fieldName = method.getName().substring(3);
                    fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
                    Object value = method.invoke(obj);
                    if (value != null) {
                        map.put(fieldName, value.toString());
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }

        return map;
    }
}
