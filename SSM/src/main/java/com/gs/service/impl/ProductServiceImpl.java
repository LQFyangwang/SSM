package com.gs.service.impl;

import com.gs.bean.Product;
import com.gs.dao.ProductDAO;
import com.gs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product getById(Integer id) {
        return productDAO.getById(id);
    }

    @Override
    public List<Product> listAll() {
        return productDAO.listAll();
    }

    @Override
    public Product getByIdTitle(Integer id, String title) {
        return productDAO.getByIdTitle(id, title);
    }

    @Override
    public int save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public int save1(Product product, String title) {
        return productDAO.save1(product, title);
    }

    @Override
    public int update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public int removeById(Integer id) {
        return productDAO.removeById(id);
    }

    @Override
    public List<Product> listByCriteria(String title, String des) {
        return productDAO.listByCriteria(title, des);
    }

    @Override
    public List<Product> listByIds(Integer[] ids) {
        return productDAO.listByIds(ids);
    }

    @Transactional // 开启事务管理
    @Override
    public int batchSave(List<Product> productList) {
        return productDAO.batchSave(productList);
    }

    /**
     * 此方法不可行，在项目中不会出现类似的情况
     * @param productList
     * @return
     */
    @Transactional
    @Override
    public int batchUpdate(List<Product> productList) {
        return productDAO.batchUpdate(productList);
    }
}
