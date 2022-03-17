package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.constans.SystemConstans;
import com.summer.entity.Article;
import com.summer.entity.Category;
import com.summer.entity.R;
import com.summer.entity.vo.CategoryVo;
import com.summer.mapper.CategoryMapper;
import com.summer.service.ArticleService;
import com.summer.service.CategoryService;
import com.summer.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-03-17 12:26:13
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private ArticleService articleService;

    @Override
    public R getCategoryList() {
        // 查询文章表，状态为已发布的文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstans.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(queryWrapper);
        // 获取文章的分类ID,并且去重
        Set<Long> categoryIds = articles.stream().map(article -> article.getCategoryId()).collect(Collectors.toSet());
        // 查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories.stream().filter(category ->
                category.getStatus().equals(SystemConstans.STATUS_NORMAL)
        ).collect(Collectors.toList());
        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return R.okResult(categoryVos);
    }
}
