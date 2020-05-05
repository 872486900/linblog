package com.lzx.linblog.web.admin;

import com.lzx.linblog.po.Tag;
import com.lzx.linblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by 87248 on 2020-04-19 16:48
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //分页
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    //调转到tags-input
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    //添加标签
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagService.savaTag(tag);
        if (t==null){
            attributes.addFlashAttribute("message","标签添加失败");
        }else {
            attributes.addFlashAttribute("message","标签添加成功");
        }
        return "redirect:/admin/tags";

    }

    //删除biaoq
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes attributes){
        attributes.addFlashAttribute("message","删除成功");
        tagService.deleteTag(id);
       return "redirect:/admin/tags";
    }

    //修改分类
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes
    ){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagService.updateTag(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","标签更新失败");
        }else {
            attributes.addFlashAttribute("message","标签更新成功");
        }
        return "redirect:/admin/tags";

    }
}
