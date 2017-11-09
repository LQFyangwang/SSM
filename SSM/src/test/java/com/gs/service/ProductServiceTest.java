package com.gs.service;

import com.gs.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest extends BaseTest{

    @Autowired
    private ProductService productService;

    @Test
    public void testSave() {
        Product product = new Product(); // Mock 模拟的，拟造
        product.setTitle("p1");
        product.setDes("des");
        product.setPrice(BigDecimal.valueOf(9.99));
        productService.save(product);
    }

    @Test
    public void testSave1() {
        Product product = new Product(); // Mock 模拟的，拟造
        product.setTitle("p1");
        product.setDes("des");
        product.setPrice(BigDecimal.valueOf(9.99));
        System.out.println(productService.save1(product, "title"));
    }

    @Test
    public void testUpdate() {
        Product product = new Product(); // Mock 模拟的，拟造
        product.setId(5);
        product.setTitle("华为");
        product.setDes("des");
        System.out.println(productService.update(product));
    }

    @Test
    public void testRemoveById() {
        System.out.println(productService.removeById(1));
    }

    @Test
    public void testListByCriteria() {
        List<Product> productList = productService.listByCriteria("", "华");
        for (Product p : productList) {
            System.out.println(p.getTitle());
        }
    }

    @Test
    public void testListByIds() {
        List<Product> productList = productService.listByIds(new Integer[]{2, 3, 4});
        for (Product p : productList) {
            System.out.println(p.getTitle());
        }
    }

    @Test
    public void testBatchSave() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product p = new Product();
            p.setTitle("pro" + i);
            p.setDes("des" + i);
            productList.add(p);
        }
        System.out.println(productService.batchSave(productList));
    }

    @Test
    public void testBatchUpdate() {
        List<Product> productList = new ArrayList<>();
        Product p = new Product();
        p.setId(2);
        p.setTitle("new2");
        Product p1 = new Product();
        p1.setId(3);
        p1.setTitle("new3");
        productList.add(p);
        productList.add(p1);
        System.out.println(productService.batchUpdate(productList));
    }

}
