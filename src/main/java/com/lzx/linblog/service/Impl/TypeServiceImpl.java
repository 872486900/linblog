package com.lzx.linblog.service.Impl;

import com.lzx.linblog.NotFoundException;
import com.lzx.linblog.dao.TypeRepository;
import com.lzx.linblog.po.Type;
import com.lzx.linblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 87248 on 2020-04-19 13:22
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    //新增分类
    @Transactional
    @Override
    public Type saveTpye(Type type) {
        return typeRepository.save(type);
    }
    //根据id查询分类
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    //分页查询
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t=typeRepository.getOne(id);
        if (t==null){
            throw  new NotFoundException("不存在该类");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        PageRequest pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }
}
