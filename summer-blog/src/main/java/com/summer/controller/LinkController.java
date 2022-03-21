package com.summer.controller;

import com.summer.entity.R;
import com.summer.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Summer
 * @date 2022/3/21 13:01
 */
@RestController
@RequestMapping("links")
public class LinkController {
    @Resource
    private LinkService linkService;
    @GetMapping()
    public R index() {
        return linkService.getAllLinks();
    }
}
