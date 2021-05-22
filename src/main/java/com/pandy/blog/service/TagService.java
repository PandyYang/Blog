package com.pandy.blog.service;

import com.pandy.blog.common.PageResult;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.dto.CategoryDTO;
import com.pandy.blog.dto.TagDTO;
import com.pandy.blog.vo.TagAddDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pandy
 */
public interface TagService{

    List<Tag> getTagList();

    PageResult<TagDTO> listTag(String tagName, int current, int size);

    void upsert(TagAddDTO tagAddDTO);

    boolean delete(int tagId);
}
