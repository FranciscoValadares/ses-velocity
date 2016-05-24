package com.easikoglu.ses.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by easikoglu on 04/05/16.
 */
public class EmailUtil {

    public static Map<String, Object> convertObjectToMap(Object obj) throws
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {

        Class<?> pomclass = obj.getClass();
        pomclass = obj.getClass();
        Method[] methods = obj.getClass().getMethods();


        Map<String, Object> map = new HashMap<String, Object>();
        for (Method m : methods) {
            if (m.getName().startsWith("get") && !m.getName().startsWith("getClass")) {
                Object value = (Object) m.invoke(obj);

                String key = m.getName();
                key = key.substring(3);

                char c[] = key.toCharArray();
                c[0] = Character.toLowerCase(c[0]);
                key = new String(c);

                map.put(key, (Object) value);
            }
        }
        return map;
    }


  /*  public static Map<String, Object> toKeyValuePairs(Object instance) {
        return Arrays.stream(EmailProperties.class.getDeclaredMethods())
                .filter(p -> !p.getName().startsWith("set"))
                .filter(p -> !p.getName().startsWith("getClass"))
                .filter(p -> !p.getName().startsWith("setClass"))
                .collect(Collectors.toMap(
                        d -> d.getName().substring(3),
                        m -> {
                            try {
                                Object result = m.invoke(instance);
                                return result != null ? result : "";
                            } catch (Exception e) {
                                return "";
                            }
                        }, (p1, p2) -> p1)
                );
    }*/
}
