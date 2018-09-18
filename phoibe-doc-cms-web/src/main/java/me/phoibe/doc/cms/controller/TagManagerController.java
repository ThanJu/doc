package me.phoibe.doc.cms.controller;

import com.alibaba.fastjson.JSON;
import me.phoibe.doc.cms.domain.po.PhoibeTag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("phoibe/tagManager")
public class TagManagerController {


    @GetMapping("/tagform")
    public String gettagForm(Model model) {
        model.addAttribute("phoibeTag", new PhoibeTag());
        return "greeting";
    }

    @PostMapping("/tagform")
    public String tagformSubmit(@ModelAttribute PhoibeTag phoibeTag) {
        System.out.println("表单提交"+ JSON.toJSONString(phoibeTag));
        return "result";
    }
}
