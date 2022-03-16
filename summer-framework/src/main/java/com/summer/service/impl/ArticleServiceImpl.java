package com.summer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Article;
import com.summer.mapper.ArticleMapper;
import com.summer.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @Author Summer
 * @Date 2022/3/17 1:24 AM
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
