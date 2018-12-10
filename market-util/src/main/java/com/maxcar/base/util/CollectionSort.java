package com.maxcar.base.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author songxuefeng
 * @create 2018-12-04 10:59
 * @description: ${description}
 **/
public class CollectionSort<String,T> {


    /**
     * 让 Map按key进行排序
     */
    public Map<String, T> sortMapByKey(Map<String, T> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String,T> sortMap = new TreeMap<String, T>((Comparator<? super String>) new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

}
