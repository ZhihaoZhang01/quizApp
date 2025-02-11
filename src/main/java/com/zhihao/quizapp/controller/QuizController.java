package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.dao.IQuizQuestionDAO;
import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.model.QuizHistory;
import com.zhihao.quizapp.model.QuizQuestionDetail;
import com.zhihao.quizapp.model.User;
import com.zhihao.quizapp.service.HomeService;
import com.zhihao.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-01 8:13 PM
 */


@Controller
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    @Lazy
    private IQuizQuestionDAO quizQuestionDAO;
    @Autowired
    private HomeService homeService;

    @GetMapping("/quiz/start")
    public String startQuiz(@RequestParam("categoryId") int categoryId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Quiz openQuiz = homeService.getOpenQuizForUser(user.getUserId());
        if (openQuiz != null) {
            return "redirect:/quiz?quizId=" + openQuiz.getQuizId();
        }
        String quizName = "Quiz-" + System.currentTimeMillis();
        int quizId = quizService.createNewQuiz(user.getUserId(), categoryId, quizName);
        return "redirect:/quiz?quizId=" + quizId;
    }

    @GetMapping("/quiz1")
    public String showQuiz(@RequestParam("quizId") int quizId, Model model, HttpSession session) {
        List<QuizQuestionDetail> quizDetails = quizService.getQuizQuestionDetails(quizId);
        long remainingTime = quizService.countTime(quizId);
        model.addAttribute("quizDetails", quizDetails);
        model.addAttribute("quizId", quizId);
        model.addAttribute("remainingTime",remainingTime);
        return "quiz";
    }

    @PostMapping("/quiz/submit")
    @Transactional
    public String submitQuiz(@RequestParam("quizId") int quizId, HttpServletRequest request) {
        Map<Integer, Integer> answers = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("answer_")){
                String qqIdStr = paramName.substring("answer_".length());
                try {
                    int qqId = Integer.parseInt(qqIdStr);
                    int choiceId = Integer.parseInt(request.getParameter(paramName));
                    answers.put(qqId, choiceId);
                } catch (NumberFormatException e) {
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : answers.entrySet()){
            quizQuestionDAO.updateUserChoice(entry.getKey(), entry.getValue());
        }
        quizService.updateEndTime(quizId);
        return "redirect:/quiz/result?quizId=" + quizId;
    }

    @GetMapping("/quiz/result")
    public String showQuizResult(@RequestParam("quizId") int quizId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        QuizHistory history = homeService.getQuizHistoryByQuizId(quizId, user);
        model.addAttribute("quizHistory", history);
        List<QuizQuestionDetail> quizDetails = quizService.getQuizQuestionDetails(quizId);
        model.addAttribute("quizDetails", quizDetails);
        return "quiz_result";
    }
}
