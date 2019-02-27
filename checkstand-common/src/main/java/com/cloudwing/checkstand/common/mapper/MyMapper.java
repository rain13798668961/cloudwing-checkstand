package com.cloudwing.checkstand.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义通用mapper, 包含普通的增删改查操作及批量插入
 * @author yangyuantao
 *
 * @param <T>
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyMapper<T> extends
        Mapper<T>,
        MySqlMapper<T> {

}
