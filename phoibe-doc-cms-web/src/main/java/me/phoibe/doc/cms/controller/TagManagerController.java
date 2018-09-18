package me.phoibe.doc.cms.controller;

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
        return "result";
    }
}
