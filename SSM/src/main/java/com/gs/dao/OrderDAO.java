package com.gs.dao;

import com.gs.bean.Order;
import com.gs.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO {

    int save(Order order);

    List<Order> listOrdersByCustomerId(@Param("customerId") Integer customerId, @Param("pager") Pager<Order> pager);

    int countByCustomerId(Integer customerId);

}
