package com.pandy.blog.controller;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.common.Result;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.dto.TagDTO;
import com.pandy.blog.service.TagService;
import com.pandy.blog.vo.CategoryAddDTO;
import com.pandy.blog.vo.TagAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("listTags")
    public Result getArticleList(@RequestParam(name = "current", required = true) int current,
                                 @RequestParam(name = "size", required = true) int size,
                                 @RequestParam(name = "tagName", required = false) String tagName) {
        final PageResult<TagDTO> TagDTOPageResult = tagService.listTag(tagName, current, size);
        return Result.success().data("data", TagDTOPageResult);
    }

    @PostMapping("addOrEditTag")
    Result upsert(@RequestBody TagAddDTO data) {
        tagService.upsert(data);
        return Result.success();
    }

    @DeleteMapping("deleteTag")
    Result delete(@RequestParam int tagId) {
       if (tagService.delete(tagId)) {
           return Result.success();
       }else {
           return Result.error();
       }
    }
}
