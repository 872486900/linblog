package com.lzx.linblog.service;

import com.lzx.linblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 87248 on 2020-04-19 13:18
 */
public interface TypeService {

    //新增分类
    Type saveTpye(Type type);
    //根据id查询分类
    Type getType(Long id);

    //分类查询
    Page<Type> listType(Pageable pageable);
    //修改分类
    Type updateType(Long id,Type type);
    //删除分类
    void deleteType(Long id);
    //
    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);


}
