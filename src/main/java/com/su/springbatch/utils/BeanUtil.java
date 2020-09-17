package com.su.springbatch.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sukang
 */
public class BeanUtil {

    private static final  ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //key可以不带双引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //key value 可以是单引号
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ;
        //允许出现特殊字符和转义符
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }


    public static String toJsonStr (Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }catch (Exception e){
            logger.error("json转换异常",e);
        }
        return "";
    }

    public static Map<String,Object> bean2map(Object bean) throws Exception{
        Map<String,Object> map = new HashMap<>(bean.getClass().getDeclaredFields().length);
        //获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
        //获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        //对属性迭代
        for (PropertyDescriptor pd : pds) {
            //属性名称
            String propertyName = pd.getName();
            //属性值,用getter方法获取
            Method m = pd.getReadMethod();

            //用对象执行getter方法获得属性值
            Object properValue = m.invoke(bean);

            //把属性名-属性值 存到Map中
            map.put(propertyName, properValue);
        }
        return map;
    }

}
