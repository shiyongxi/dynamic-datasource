package com.yx.samples.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.samples.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}