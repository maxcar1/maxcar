/**
 * @Author：罗顺锋
 * @Create：2017/2/22 15:33
 */
package com.maxcar.core.base.service;

import java.io.Serializable;

import com.maxcar.core.base.pojo.InterfaceResult;

/**
 * 业务逻辑层，通用接口
 *
 * @auther yangzhi 罗顺锋
 * @create 2018/4/20
 */
public interface BaseService<T,ID extends Serializable> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     * 通用根据主键删除数据
     * @mbggenerated
     */
    int deleteByPrimaryKey(ID id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     *通用插入数据
     * @mbggenerated
     */
    int insert(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     *通用有选择性插入部分数据
     * @mbggenerated
     */
    int insertSelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     *通用根据主键查询数据
     * @mbggenerated
     */
    T selectByPrimaryKey(ID id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     *通用有选择性更新对象，注意必须有主键id
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T
     *通用更新对象，注意必须有主键id
     * @mbggenerated
     */
    int updateByPrimaryKey(T record);
}