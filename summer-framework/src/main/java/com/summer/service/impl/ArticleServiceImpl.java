package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.constans.SystemConstans;
import com.summer.entity.Article;
import com.summer.entity.Category;
import com.summer.entity.R;
import com.summer.entity.vo.ArticleDetailVo;
import com.summer.entity.vo.ArticleListVo;
import com.summer.entity.vo.HotArticleVo;
import com.summer.entity.vo.PageVo;
import com.summer.mapper.ArticleMapper;
import com.summer.service.ArticleService;
import com.summer.service.CategoryService;
import com.summer.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Summer
 * @Date 2022/3/17 1:24 AM
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private CategoryService categoryService;

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

    @Override
    public R getArticleList(Integer page_num, Integer page_size, Long category_id) {
        // 查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(category_id), Article::getCategoryId, category_id);
        queryWrapper.eq(Article::getStatus, SystemConstans.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(page_num == null ? page_num : 1, 1);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        articles.parallelStream().map(article ->
                article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())
        ).collect(Collectors.toList());
        // 封查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return R.okResult(pageVo);
    }

    @Override
    public R getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        // 转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类ID查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }

        return R.okResult(articleDetailVo);
    }

}
