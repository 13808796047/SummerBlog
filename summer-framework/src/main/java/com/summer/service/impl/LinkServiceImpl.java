package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.constans.SystemConstans;
import com.summer.entity.Link;
import com.summer.entity.R;
import com.summer.entity.vo.LinkVo;
import com.summer.mapper.LinkMapper;
import com.summer.service.LinkService;
import com.summer.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-03-21 13:06:33
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public R getAllLinks() {
        // 查询所有审核通过的
        LambdaQueryWrapper<Link> queryWapper = new LambdaQueryWrapper<>();
        queryWapper.eq(Link::getStatus, SystemConstans.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWapper);
        // 转换成vo
        List<LinkVo> linksVo = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        // 封装返回
        return R.okResult(linksVo);
    }
}
