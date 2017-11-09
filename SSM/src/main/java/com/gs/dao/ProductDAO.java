package com.gs.dao;

import com.gs.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO {

    Product getById(Integer id);
    List<Product> listAll();
    Product getByIdTitle(@Param("i") Integer id, @Param("title") String title); // 真实项目不会出现此种发问
    int save(Product product);
    int save1(@Param("product") Product product, @Param("title") String title); // 真实项目中此种情况不出现
    int update(Product product);
    int removeById(Integer id);
    List<Product> listByCriteria(@Param("title") String title, @Param("des") String des);
    List<Product> listByIds(Integer[] ids);
    int batchSave(List<Product> productList);
    int batchUpdate(List<Product> productList);
}
