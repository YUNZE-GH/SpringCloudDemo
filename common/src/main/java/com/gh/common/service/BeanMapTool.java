package com.gh.common.service;

import java.util.List;
import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/9 18:35
 */
public interface BeanMapTool {

    /**
     * 实体类转Map
     *
     * @param bean 实体类
     * @param <T>  实体类类型
     * @return Map<String, Object>
     */
    <T> Map<String, Object> beanToMap(T bean);

    /**
     * Map转实体类
     *
     * @param map   Map<String, Object>集合
     * @param clazz 转换目标实体类类型
     * @param <T>   实体类类型
     * @return clazz类型的实体类
     * @throws Exception
     */
    <T> T mapToBean(Map<String, ?> map, Class<T> clazz) throws Exception;

    /**
     * List<实体类>转换为List<Map<String, Object>>集合
     *
     * @param objList List<实体类>入参
     * @param <T>     实体类类型
     * @return List<Map < String, Object>>集合
     */
    <T> List<Map<String, ?>> objectsToMaps(List<T> objList);

    /**
     * List<Map<String, Object>>集合转换为List<实体类>
     *
     * @param maps  List<Map<String, Object>>数据集合
     * @param clazz map转换目标实体类
     * @param <T>   转换目标实体类类型
     * @return List<实体类>
     * @throws Exception
     */
    <T> List<T> mapsToObjects(List<Map<String, ?>> maps, Class<T> clazz) throws Exception;
}
