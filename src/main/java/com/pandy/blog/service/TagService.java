package com.pandy.blog.service;

import com.pandy.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pandy
 */
public interface TagService{

    List<Tag> getTagList();
}
