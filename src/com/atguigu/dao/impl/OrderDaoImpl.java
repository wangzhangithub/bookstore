package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println(" OrderDaoImpl 程序在[" +Thread.currentThread().getName() + "]中");

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrder_id(),order.getCreate_time(),order.getPrice(),order.getStatus(),order.getUser_id());
    }
    public Order queryOrderByOrderId(String orderId) {
        String sql="select `order_id`,`create_time`,`price`,`status`,`user_id` from `t_order` where order_id=?";
        return queryForOne(Order.class,sql,orderId);
    }

    public int changeOrderStatus(String orderId, int status) {
        String sql="update `t_order` set status=? where order_id=?";
        return update(sql, status,orderId);
    }

    public List<Order> queryOrdersByUserId(int id) {
        // TODO Auto-generated method stub
        String sql="select `order_id`,`create_time`,`price`,`status`,`user_id` from `t_order` where user_id=?";
        return queryForList(Order.class,sql,id);
    }
    public List<Order> queryForPageOrder(int begin, int pageSize) {
        String sql = "select `order_id`,`create_time`,`price`,`status`,`user_id` from t_order limit ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_order";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }


}
