package com.pandy.blog.service.impl;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.dao.ArticleTagDao;
import com.pandy.blog.dao.TagDao;
import com.pandy.blog.domain.ArticleTag;
import com.pandy.blog.domain.Category;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.dto.TagDTO;
import com.pandy.blog.service.TagService;
import com.pandy.blog.vo.TagAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pandy
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Autowired
    ArticleTagDao articleTagDao;

    @Override
    public List<Tag> getTagList() {
        return tagDao.findAll();
    }

    @Override
    public PageResult<TagDTO> listTag(String tagName, int current, int size) {
        Page<Tag> categoryDTOS = null;
        if (StringUtils.isEmpty(tagName)) {
            categoryDTOS = tagDao.findAll(PageRequest.of(current - 1, size));
        } else {
            categoryDTOS = tagDao.findAllByTagContaining(tagName, PageRequest.of(current - 1, size));
        }
        final List<Tag> content = categoryDTOS.getContent();
        List<TagDTO> categoryDTOList = new ArrayList<>();
        for (Tag Tag : content) {
            TagDTO TagDTO = new TagDTO();
            TagDTO.setTagId(Tag.getId());
            TagDTO.setTagName(Tag.getTag());
            TagDTO.setUpdateTime(Tag.getUpdateTime());
            Tag.setCreateTime(Tag.getCreateTime());
            categoryDTOList.add(TagDTO);
        }
        PageResult<TagDTO> categoryDTOPageResult = new PageResult<>();
        categoryDTOPageResult.setItems(categoryDTOList);
        categoryDTOPageResult.setTotal((int) categoryDTOS.getTotalElements());
        return categoryDTOPageResult;
    }

    @Override
    public void upsert(TagAddDTO tagAddDTO) {
        Date now = new Date();
        Tag tag = new Tag();
        if (tagAddDTO.getTagId() == null) {
            tag.setCreateTime(now);
            tag.setUpdateTime(now);
            tag.setTag(tagAddDTO.getTagName());
        }else {
            Tag tag1 = tagDao.findById(tagAddDTO.getTagId()).get();
            tag.setDeleteTime(tag1.getDeleteTime());
            tag.setCreateTime(tag1.getCreateTime());
            tag.setUpdateTime(tag1.getUpdateTime());
            tag.setTag(tagAddDTO.getTagName());
            tag.setId(tagAddDTO.getTagId());
        }
        tagDao.save(tag);
    }

    @Override
    public boolean delete(int tagId) {
        if (!articleTagDao.existsByTagId(tagId)) {
            tagDao.deleteById(tagId);
            return true;
        }else {
            return false;
        }
    }
}
