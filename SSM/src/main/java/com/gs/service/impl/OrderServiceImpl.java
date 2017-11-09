package com.gs.service.impl;

import com.gs.bean.Order;
import com.gs.bean.OrderDetail;
import com.gs.bean.Pager;
import com.gs.dao.OrderDAO;
import com.gs.dao.OrderDetailDAO;
import com.gs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public int save(Order order) {
        int count = orderDAO.save(order);
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(order.getId());
        }
        orderDetailDAO.batchSave(orderDetailList); // 需要orderId
        return count;
    }

    @Override
    public Pager<Order> listOrdersByCustomerId(Integer customerId, Pager<Order> pager) {
        List<Order> orders = orderDAO.listOrdersByCustomerId(customerId, pager);
        pager.setResults(orders);
        pager.setTotal(orderDAO.countByCustomerId(customerId));
        return pager;
    }
}
