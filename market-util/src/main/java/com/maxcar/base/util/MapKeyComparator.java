package com.maxcar.base.util;

import java.util.Comparator;

/**
 * @author songxuefeng
 * @create 2018-12-04 11:12
 * @description: ${description}
 **/
class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);  //从大到小排序
    }
}

