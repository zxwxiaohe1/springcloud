package com.self.eureka.springjpa.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Discrption: bean 工具类
 * @Date: 2017-12-13
 */
public abstract class BeanUtil {

    public static List<String> defaultIgnoreProperites = new ArrayList<String>(
            Arrays.asList("id", "createBy", "createDate", "updateBy", "updateDate", "parentId", "parentIds"));

    /**
     * 获得值为空的属性名
     *
     * @param source Object
     * @return String[]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (ObjectUtils.isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 复制对象，只复制值不为空的属性
     *
     * @param src    Object
     * @param target Object
     * @return
     * @throws
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 对比对象属性
     *
     * @param obj1
     * @param obj2
     * @return
     * @Title: compareFields
     * @Description:
     * @return: Map<String, List < Object>>
     */
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2) {
        return compareFields(obj1, obj2, null);
    }

    /**
     * 对比对象属性
     *
     * @param obj1
     * @param obj2
     * @param ignoreList
     * @return
     * @Title: compareFields
     * @Description:
     * @return: Map<String, List < Object>>
     */
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, List<String> ignoreList) {
        try {
            if (ignoreList == null) {
                ignoreList = new ArrayList<String>();
                // array转化为list
            }
            ignoreList.addAll(defaultIgnoreProperites);
            Map<String, List<Object>> map = new HashMap<String, List<Object>>();
            if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性
                Class<?> clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了
                    String name = pd.getName();// 属性名
                    if (ignoreList != null && ignoreList.contains(name)) {// 如果当前属性选择忽略比较，跳到下一次循环
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();// get方法
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    if (o1 == null && o2 == null) {
                        continue;
                    } else if (o1 == null && o2 != null) {
                        if (!isBaseType(o2)) { // 只比较基础类型
                            continue;
                        }
                        List<Object> list = new ArrayList<Object>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                        continue;
                    }
                    if (!isBaseType(o1)) { // 只比较基础类型
                        continue;
                    }
                    if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了
                        List<Object> list = new ArrayList<Object>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String compareFieldsResult(Object obj1, Object obj2) {
        Map<String, List<Object>> fieldMap = compareFields(obj1, obj2);
        StringBuilder message = new StringBuilder();
        if (fieldMap.size() > 0) {
            message.append("修改字段：\n");
            for (String field : fieldMap.keySet()) {
                message.append(field).append("修改前为").append(fieldMap.get(field).get(0)).append("，修改后为").append(fieldMap.get(field).get(1)).append("\n");
            }
        }
        return message.toString();
    }

    /**
     * 判断是否基本类型
     *
     * @param object
     * @return
     * @Title: isBaseType
     * @Description:
     * @return: boolean
     */
    public static boolean isBaseType(Object object) {
        Class<?> className = object.getClass();
        if (className.equals(String.class) || className.equals(Integer.class) || className.equals(Byte.class)
                || className.equals(Long.class) || className.equals(Double.class) || className.equals(Float.class)
                || className.equals(Character.class) || className.equals(Short.class) || className.equals(Boolean.class)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
