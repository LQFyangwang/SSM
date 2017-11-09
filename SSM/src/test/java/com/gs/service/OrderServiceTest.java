package com.gs.service;

import com.gs.bean.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OrderServiceTest extends BaseTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setId(1);
        Product product = new Product();
        product.setId(5);
        Product product1 = new Product();
        product1.setId(2);
        Order order = new Order();
        order.setOrderTime(Calendar.getInstance().getTime());
        order.setCustomerId(customer.getId());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId(product.getId());
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId(product1.getId());
        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);
        order.setOrderDetailList(orderDetailList);
        orderService.save(order);
    }

    @Test
    public void testListOrdersByCustomerId() {
        Pager<Order> pager = new Pager<>();
        pager.setPageNo(1);
        pager.setPageSize(2);
        pager = orderService.listOrdersByCustomerId(1, pager);
        if (pager.getResults() != null && pager.getResults().size() > 0) {
            for (Order order : pager.getResults()) {
                System.out.println(order.getId() + ", " + order.getOrderTime() + ", " + order.getOrderDetailList());
                System.out.println(order.getCustomer().getId() + ", " + order.getCustomer().getName());
                for (OrderDetail orderDetail : order.getOrderDetailList()) {
                    System.out.println(orderDetail.getId() + ", " + orderDetail.getProduct().getTitle());
                }
            }
        }
    }

}
