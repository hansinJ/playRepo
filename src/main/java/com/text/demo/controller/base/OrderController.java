package com.text.demo.controller.base;

import com.text.demo.entity.OrderEntity;
import com.text.demo.service.base.OrderService;
import com.text.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 订单模块
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("list")
    @ResponseBody
    public JsonResult orderList(String payStatus,String playName,Integer id,Integer currentPage, Integer nowPageSize) {
        JsonResult res = new JsonResult();
        try {
            Map<String,Object> data = orderService.orderList(payStatus,playName,id,currentPage,nowPageSize);
            res.setData(data);
            res.setCode(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("orderSave")
    @ResponseBody
    public JsonResult orderSave(OrderEntity orderEntity) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = orderService.orderSave(orderEntity);
            res.setMsg(data);
        }catch (Exception e){
            res.setData(false);
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("orderEdit")
    @ResponseBody
    public JsonResult orderEdit(OrderEntity orderEntity) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = orderService.orderEdit(orderEntity);
            res.setData(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("orderDetail")
    @ResponseBody
    public JsonResult driverSelect(int id) {
        JsonResult res = new JsonResult();
        try {
            Map<String,Object> data = orderService.orderSelect(id);
            res.setData(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("orderDel")
    @ResponseBody
    public JsonResult deleteData(int[] ids) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = orderService.orderDel(ids);
            res.setData(data);
        }catch (Exception e){
            res.setData(false);
            e.printStackTrace();
        }
        return res;
    }
}
