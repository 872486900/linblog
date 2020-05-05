package com.lzx.linblog.service.Impl;

import com.lzx.linblog.NotFoundException;
import com.lzx.linblog.dao.TagRepository;
import com.lzx.linblog.po.Tag;
import com.lzx.linblog.service.BlogService;
import com.lzx.linblog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 87248 on 2020-04-19 16:53
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BlogService blogService;
    //新增分类
    @Transactional
    @Override
    public Tag savaTag(Tag tag) {
        return tagRepository.save(tag);
    }
    //根据id查询分类
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }


    //分页查询
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t=tagRepository.getOne(id);
        if (t==null){
            throw  new NotFoundException("不存在该类");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);

    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTags(String ids) {//1,2,3
        return tagRepository.findAllById(converTolist(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        PageRequest pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> converTolist(String ids){
        List<Long> list=new ArrayList<>();
        if (!"".equals(ids)&& ids!=null){
            String [] idarray=ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));

            }
        }
        return list;
    }





}
