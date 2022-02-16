package com.text.demo.service.base.impl;

import com.text.demo.entity.OrderEntity;
import com.text.demo.entity.PeopleEntity;
import com.text.demo.mapper.base.OrderMapper;
import com.text.demo.mapper.base.PeopleMapper;
import com.text.demo.service.base.OrderService;
import com.text.demo.utils.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PeopleMapper peopleMapper;

    @Override
    public Map<String,Object> orderList(String payStatus,String playName,Integer tradeId,Integer currentPage,Integer nowPageSize) {
        int current = (currentPage-1)*nowPageSize;
//        if (null != payStatus  || !payStatus.equals("")){
//            payStatus = "%"+payStatus+"%";
//        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<OrderEntity> res = orderMapper.orderList(payStatus,playName,tradeId,current,nowPageSize);
        int total = orderMapper.getTotal(payStatus,playName,tradeId);
        for (OrderEntity obj:res) {
            if(null != obj.getPayTimeDate() && !obj.getPayTimeDate().equals("")){
                obj.setPayTime(simpleDateFormat.format(obj.getPayTimeDate()));
            }
            obj.setPlayStateList(peopleMapper.getPlayStateListById(obj.getPlayId()));
        }
        Map<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("row",res);
        data.put("current",currentPage);
        data.put("nowPageSize",nowPageSize);
        return data;
    }

    @Override
    public Boolean orderSave(OrderEntity orderEntity) {
        orderEntity.setCreateTime(new Date());
        int res =  orderMapper.orderSave(orderEntity);
        return ReturnData.returnData(res);
    }

    @Override
    public Boolean orderEdit(OrderEntity orderEntity) {
        int res =  orderMapper.orderEdit(orderEntity);
        return ReturnData.returnData(res);
    }

    @Override
    public Map<String, Object> orderSelect(int id) {
        Map<String, Object> orderMap = new HashMap<>();
        OrderEntity orderData = orderMapper.orderSelectById(id);
        orderMap.put("orderData",orderData);
        return orderMap;
    }

    @Override
    public Boolean orderDel(int[] ids) {
        int res =  orderMapper.deleteData(ids);
        return ReturnData.returnData(res);
    }
}
