package com.atguigu.dao;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);
    public Order queryOrderByOrderId(String orderId);
    public int changeOrderStatus(String orderId, int status);
    public List<Order> queryOrdersByUserId(int id) ;
    public List<Order> queryForPageOrder(int begin, int pageSize);
    public Integer queryForPageTotalCount();

}
