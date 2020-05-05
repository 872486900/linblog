package com.lzx.linblog.dao;

import com.lzx.linblog.po.Tag;
import com.lzx.linblog.po.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 87248 on 2020-04-19 16:54
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    @Query("select t from Tag  t")
    List<Tag> findTop(PageRequest pageable);

}
