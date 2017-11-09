package com.gs.service;

import com.gs.bean.Order;
import com.gs.bean.OrderDetail;
import com.gs.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderService {

    int save(Order order);

    Pager<Order> listOrdersByCustomerId(Integer customerId, Pager<Order> pager);

}
