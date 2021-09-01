package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.Page;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public int sendOrder(String orderId);
    public List<OrderItem> showOrderDetail(String orderId);
    public int receiveOrder(String orderId);
    public List<Order> showMyOrders(int userId);
    public Page<Order> page(int pageNo, int pageSize);
}
