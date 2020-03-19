package com.self.cloud.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * @author HY
 * @date 2019/7/5 10:25
 */
public enum JacksonUtils {
    ;
    private static ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * @param obj Object类型
     * @return String对象
     * @throws IOException
     */
    public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    /**
     * @param jsonStr  String类型
     * @param objClass Class<T>
     * @return T泛型对象
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass)
            throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }

    /**
     * 将json转换为复杂集合对象
     *
     * @param jsonStr
     * @param collectionClass
     * @param elementClasses
     * @return T
     */
    public static <T> T json2JavaType(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses)
            throws JsonProcessingException {
        if (StringUtils.isEmpty(jsonStr) || collectionClass == null || elementClasses.length <= 0) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
        return String.class.equals(collectionClass) ? (T) jsonStr : (T) mapper.readValue(jsonStr, javaType);
    }
}
