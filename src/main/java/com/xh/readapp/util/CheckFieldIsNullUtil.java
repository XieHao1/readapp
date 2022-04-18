package com.xh.readapp.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CheckFieldIsNullUtil {

    public static Map<String,Object> checkObjectFieldIsNull(Object object){
        Map<String,Object> map = new HashMap<>();

        if(object==null){
            return null;
        }

        //获得类对象
        Class<?> clazz = object.getClass();
        //获得对象的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        //遍历对象
        for (Field field : declaredFields){
            //打破封装
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                //获取属性的值
                fieldValue = field.get(object);
                //获取属性的名字
                fieldName = field.getName();
                if("serialVersionUID".equals(fieldName)){
                    continue;
                }
                if(fieldValue==null){
                    continue;
                }
                map.put(fieldName,fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
