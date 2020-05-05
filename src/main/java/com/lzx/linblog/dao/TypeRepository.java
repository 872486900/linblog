package com.lzx.linblog.dao;

import com.lzx.linblog.po.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by 87248 on 2020-04-19 13:23
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    @Query("select t from Type  t")
    List<Type> findTop(PageRequest pageable);
}
