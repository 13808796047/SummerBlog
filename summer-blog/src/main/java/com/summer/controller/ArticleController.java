package com.summer.controller;

import com.summer.entity.Article;
import com.summer.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @GetMapping
    public List<Article> test(){
        return articleService.list();
    }
}
