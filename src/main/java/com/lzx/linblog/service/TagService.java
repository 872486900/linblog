package com.lzx.linblog.service;

import com.lzx.linblog.po.Tag;
import com.lzx.linblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 87248 on 2020-04-19 16:53
 */
public interface TagService {

    //新增分类
   Tag savaTag(Tag tag);
    //根据id查询分类
    Tag getTag(Long id);
    //分类查询
    Page<Tag> listTag(Pageable pageable);
    //修改分类
    Tag updateTag(Long id,Tag tag);
    //删除分类
    void deleteTag(Long id);
    //
    Tag getTagByName(String name);
    List<Tag> listTag();

    List<Tag> listTags(String ids);

    List<Tag> listTagTop(Integer size);


}
