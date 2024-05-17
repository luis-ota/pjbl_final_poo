package utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConvertMap {
    public static <T> T mapToObject(Map<String, String> map, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                Field field = clazz.getDeclaredField(entry.getKey().toLowerCase());
                field.setAccessible(true);
                field.set(obj, entry.getValue());
            } catch (NoSuchFieldException e) {
                System.out.println("No such field: " + entry.getKey());
            } catch (IllegalAccessException e) {
                System.out.println("Illegal access to field: " + entry.getKey());
            }
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
