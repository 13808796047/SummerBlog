package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.constans.SystemConstans;
import com.summer.entity.Article;
import com.summer.entity.R;
import com.summer.entity.vo.HotArticleVo;
import com.summer.mapper.ArticleMapper;
import com.summer.service.ArticleService;
import com.summer.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Summer
 * @Date 2022/3/17 1:24 AM
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public R hotArticleList() {
        // 查询热门文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstans.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 查询10条数据
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        articles.forEach(article -> {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article, vo);
//            articleVos.add(vo);
//        });
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return R.okResult(articleVos);
    }
}
