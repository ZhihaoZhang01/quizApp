package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.Category;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizHistory;
import com.zhihao.quizapp.model.User;
import com.zhihao.quizapp.service.CategoryService;
import com.zhihao.quizapp.service.HomeService;
import com.zhihao.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-01-31 8:52 PM
 */
@Controller
public class HomeController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Category> categories = categoryService.getAllCategories();
        List<QuizHistory> historyList = homeService.getQuizHistoryForUser(user.getUserId(), user);
        Quiz openQuiz = homeService.getOpenQuizForUser(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("categories",categories);
        model.addAttribute("quizHistory", historyList);
        model.addAttribute("openQuiz", openQuiz);

        return "home";
    }


}
