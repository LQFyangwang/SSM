package com.gs.controller;

import com.gs.bean.Product;
import com.gs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    /**
     * 可以在属性上使用@Resource或@Autowired注解注入bean组件
     * 可以在setter上使用同样的注解注入bean组件
     *
     * 最终结果是一致的，都能完成自动注入，但是前者是直接使用Java反射的方式注入，破坏类的封装性
     * 而后者是使用setter方法注入的
     */
   @Autowired
    private ProductService productService;

   @GetMapping("one/{id}")
   @ResponseBody
   public Product getById(@PathVariable("id") Integer id) {
       return productService.getById(id);
   }

    @GetMapping("one/{id}/{title}")
    @ResponseBody
    public Product getById(@PathVariable("id") Integer id, @PathVariable("title") String title) {
        return productService.getByIdTitle(id, title);
    }

    @GetMapping("all")
    @ResponseBody
    public List<Product> listAll() {
        return productService.listAll();
    }
}
