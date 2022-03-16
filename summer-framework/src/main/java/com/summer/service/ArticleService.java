package com.summer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.Article;
import com.summer.entity.R;

/**
 * @Author Summer
 * @Date 2022/3/17 1:23 AM
 * @Version 1.0
 */
public interface ArticleService extends IService<Article> {
    R hotArticleList();
}
