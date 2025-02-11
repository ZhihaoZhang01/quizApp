package com.zhihao.quizapp.controller;

import com.zhihao.quizapp.model.Quiz;
import com.zhihao.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizRestController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<Quiz> getQuizzesByUser(@RequestParam("userId") int userId) {
        return quizService.getQuizByUserId(userId);
    }
}
