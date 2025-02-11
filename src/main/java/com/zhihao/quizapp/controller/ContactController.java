package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.Contact;
import com.zhihao.quizapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 11:09 PM
 */

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String showContact(Model model){
        Contact contact = new Contact();
        model.addAttribute("contact",contact);
        return "contact";
    }

    @PostMapping("/contact")
    @Transactional
    public String submitContact(@ModelAttribute("contact") Contact contact,Model model){
        contactService.createNewContact(contact);
        model.addAttribute("message","your message is sent to Admin :)");
        return "contact";
    }
}
