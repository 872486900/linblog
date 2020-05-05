package com.lzx.linblog.web.admin;

import com.lzx.linblog.po.Type;
import com.lzx.linblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by 8
 * 7248 on 2020-04-19 14:20
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model
    ) {
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }



    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());

        return "admin/types-input";
    }

    //@Valid判断校验的结果
    //添加分类
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","不能重复添加分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveTpye(type);
        if (t==null){
            redirectAttributes.addFlashAttribute("message","添加失败");
        }else {
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";
    }

    //修改分类
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","不能重复添加分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t==null){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String  delete(@PathVariable  Long id,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","删除成功");
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }


}
