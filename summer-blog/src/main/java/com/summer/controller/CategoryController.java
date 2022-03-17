package com.summer.controller;

import com.summer.entity.R;
import com.summer.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Summer
 * @Since 2022/3/17 12:19 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping
    public R index() {
        return categoryService.getCategoryList();
    }
}
