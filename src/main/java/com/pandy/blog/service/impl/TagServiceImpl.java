package com.pandy.blog.service.impl;

import com.pandy.blog.dao.TagDao;
import com.pandy.blog.domain.Tag;
import com.pandy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pandy
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> getTagList() {
        return tagDao.findAll();
    }
}
