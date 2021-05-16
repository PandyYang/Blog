package com.pandy.blog.controller;

import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pandy
 */
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/getTagList")
    Result getTagList() {
        final List<Tag> tagList = tagService.getTagList();
        return Result.success().data("data", tagList);
    }
}
