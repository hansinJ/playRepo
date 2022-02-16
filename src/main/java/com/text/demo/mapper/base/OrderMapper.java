package com.text.demo.mapper.base;

import com.text.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int orderSave(OrderEntity orderEntity);

    int orderEdit(OrderEntity orderEntity);

    int deleteData(@Param("ids") int[] ids);

    OrderEntity orderSelectById(@Param("id") int id);

    List<OrderEntity> orderList(@Param("payStatus")String payStatus,@Param("playName") String playName,@Param("tradeId") Integer tradeId,@Param("current") Integer current,@Param("nowPageSize") Integer nowPageSize);

    int getTotal(@Param("payStatus")String payStatus,@Param("playName") String playName,@Param("tradeId") Integer tradeId);
}
