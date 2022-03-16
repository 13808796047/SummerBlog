package com.summer.controller;

import com.summer.entity.R;
import com.summer.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Summer
 * @Date 2022/3/17 1:29 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("hot")
    public R hot() {
        // 查询热门文章封装成R返回
        return articleService.hotArticleList();
    }
}
