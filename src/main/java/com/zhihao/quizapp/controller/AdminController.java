package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.*;
import com.zhihao.quizapp.service.AdminService;
import com.zhihao.quizapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-02 6:27 PM
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String adminHome(HttpSession session) {
        return "admin_home";
    }

    @GetMapping("/users")
    public String manageUsers(Model model, HttpSession session, @RequestParam(value="page", defaultValue="1") int page) {
        List<User> users = adminService.getUsers(page);
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        return "admin_users";
    }

    @PostMapping("/users/toggleStatus")
    public String toggleUserStatus(@RequestParam("userId") int userId, HttpSession session) {
        adminService.toggleUserStatus(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/quizResults")
    public String manageQuizResults(Model model, HttpSession session,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "userEmail", required = false) String userEmail) {
        List<QuizHistory> quizHistories = adminService.getQuizHistories(page, category, userEmail);
        model.addAttribute("quizHistories", quizHistories);
        model.addAttribute("currentPage", page);
        model.addAttribute("filterCategory", category);
        model.addAttribute("filterUserEmail", userEmail);
        return "admin_quizResults";
    }

    // Question Management Page
    @GetMapping("/questions")
    public String manageQuestions(Model model, HttpSession session, @RequestParam(value="page", defaultValue="1") int page) {
        List<Question> questions = adminService.getQuestions(page);
        model.addAttribute("questions", questions);
        model.addAttribute("currentPage", page);
        return "admin_questions";
    }

    @PostMapping("/questions/toggleStatus")
    public String toggleQuestionStatus(@RequestParam("questionId") int questionId, HttpSession session) {
        adminService.toggleQuestionStatus(questionId);
        return "redirect:/admin/questions";
    }

    @GetMapping("/questions/add")
    public String addQuestionForm(Model model, HttpSession session) {
        model.addAttribute("questionForm", new QuestionForm());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin_questionAdd";  // 对应 /WEB-INF/jsp/admin_questionAdd.jsp
    }

    @PostMapping("/questions/add")
    public String addQuestion(@ModelAttribute("questionForm") QuestionForm questionForm, Model model, HttpSession session) {
        Question question = new Question();
        Category category = new Category();
        category.setCategoryId(questionForm.getCategoryId());
        question.setCategory(category);
        question.setDescription(questionForm.getDescription());
        question.setIsActive(1);
        List<Choice> choices = new ArrayList<>();
        String[] choiceDescriptions = new String[]{
                questionForm.getChoice1(),
                questionForm.getChoice2(),
                questionForm.getChoice3(),
                questionForm.getChoice4()
        };
        for (int i = 0; i < choiceDescriptions.length; i++) {
            Choice choice = new Choice();
            choice.setDescription(choiceDescriptions[i]);
            choice.setIsCorrect((i + 1) == questionForm.getCorrectChoice() ? 1 : 0);
            choices.add(choice);
        }
        adminService.addQuestion(question, choices);
        return "redirect:/admin/questions";
    }

    @GetMapping("/questions/edit")
    public String editQuestionForm(@RequestParam("questionId") int questionId, Model model, HttpSession session) {
        QuestionEditForm questionEditForm = adminService.getQuestionEditForm(questionId);
        model.addAttribute("questionEditForm", questionEditForm);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin_questionEdit";
    }

    @PostMapping("/questions/edit")
    public String editQuestion(@ModelAttribute("questionEditForm") QuestionEditForm questionEditForm, HttpSession session) {
        adminService.updateQuestion(questionEditForm);
        return "redirect:/admin/questions";
    }

    @GetMapping("/contacts")
    public String manageContacts(Model model, HttpSession session, @RequestParam(value="page", defaultValue="1") int page) {
        List<Contact> contacts = adminService.getContacts(page);
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        return "admin_contacts";
    }

    @GetMapping("/contacts/view")
    public String viewContact(@RequestParam("contactId") int contactId, Model model, HttpSession session) {
        Contact contact = adminService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "admin_contactDetail";
    }


}
