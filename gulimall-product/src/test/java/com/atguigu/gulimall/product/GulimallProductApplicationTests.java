package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void contextLoads() {
        Long[] path = this.categoryService.findCategoryPath(225L);
        System.out.println("path = " + path);
    }

}
