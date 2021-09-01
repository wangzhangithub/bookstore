package com.atguigu.web;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    private OrderDao orderDao=new OrderDaoImpl();
    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        System.out.println("OrderServlet程序在[" +Thread.currentThread().getName() + "]中");

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Order> page = orderService.page(pageNo,pageSize);

        page.setUrl("orderServlet?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = orderService.sendOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=page");
    }
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String orderId=req.getParameter("orderId");
    List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
    Order order= orderDao.queryOrderByOrderId(orderId);
    req.setAttribute("orderItems",orderItems);
    req.setAttribute("order",order );
    req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);


    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = orderService.receiveOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrders");
    }
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= (User) req.getSession().getAttribute("user");
        int userId=user.getId();
        List<Order> orders = orderService.showMyOrders(userId);
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/client/myOrder.jsp").forward(req,resp);

    }
}
