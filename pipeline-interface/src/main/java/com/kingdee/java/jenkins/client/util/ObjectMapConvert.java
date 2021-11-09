package com.kingdee.java.jenkins.client.util;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;


/**
* @author 高伟
* @date  2020/1/19 14:19
* @descrption 对象与Map转换类
*/
public class ObjectMapConvert {


    /**
      * @Description javabean转Map
      * @param:
      * @Return
      * @Exception
    **/
    public static  <T> Map<String,Object> beanToMap(T bean){
        Map<String, Object> map = Maps.newHashMap();
        if (null != bean){
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()){
                map.put(key+"",beanMap.get(key));
            }
        }
        return map;
    }

    /**
      * @Description 将map装换为javabean对象
      * @param:
      * @Return
      * @Exception
    **/
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
