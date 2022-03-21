package com.summer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.Link;
import com.summer.entity.R;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-03-21 13:06:33
 */
public interface LinkService extends IService<Link> {

    R getAllLinks();
}

