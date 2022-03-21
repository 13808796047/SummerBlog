package com.summer.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Summer
 * @Since 2022/3/21 10:01 AM
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
    private Long id;
    //标题
    private String title;

    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;

    private String categoryName;
    //缩略图
    private String thumbnail;
    //文章内容
    private String content;
    //访问量
    private Long viewCount;

    private Date createTime;
}
