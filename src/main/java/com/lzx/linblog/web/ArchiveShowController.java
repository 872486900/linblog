package com.lzx.linblog.web;

import com.lzx.linblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 87248 on 2020-04-21 21:47
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";

    }
}
