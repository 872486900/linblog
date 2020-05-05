package com.lzx.linblog.service;

import com.lzx.linblog.po.Blog;
import com.lzx.linblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


/**
 * Created by 87248 on 2020-04-19 21:25
 */
public interface BlogService {
    //根据id查询
    Blog getBlog(Long id);
    //分页查询
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(String query, Pageable pageable);
    Page<Blog> listBlog(Long tagId, Pageable pageable);
    //新增
    Blog saveBlog(Blog blog);
    //修改
    Blog updateBlog(Long id,Blog blog);
    //删除
    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog getAndConvert(Long id);

    //定义归纳
    Map<String,List<Blog>> archiveBlog();
    Long countBlog();

}
