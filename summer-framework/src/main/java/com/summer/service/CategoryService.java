package com.summer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.Category;
import com.summer.entity.R;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-03-17 12:26:13
 */
public interface CategoryService extends IService<Category> {

    R getCategoryList();
}

