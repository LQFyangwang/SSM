package com.gs.dao;

import com.gs.bean.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDAO {

    int save(OrderDetail orderDetail);

    int batchSave(List<OrderDetail> orderDetailList);

}
