package com.summer.controller;

import com.summer.entity.R;
import com.summer.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Summer
 * @Date 2022/3/17 1:29 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("")
    public R index(Integer page, Integer page_size, Long category_id) {
        return articleService.getArticleList(page, page_size, category_id);
    }

    @GetMapping("hot")
    public R hot() {
        // 查询热门文章封装成R返回
        return articleService.hotArticleList();
    }

    @GetMapping("{id}")
    public R show(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}
