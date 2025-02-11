package com.zhihao.quizapp.service;

import com.zhihao.quizapp.dao.*;
import com.zhihao.quizapp.dao.jdbc.ChoiceDaoJdbcImpl;
import com.zhihao.quizapp.dao.IUserDAO;
import com.zhihao.quizapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-02 6:27 PM
 */

@Service
public class AdminService {
    @Autowired
    @Lazy
    private IUserDAO userDAO;

    @Autowired
    @Lazy
    private IQuizDAO quizDAO;

    @Autowired
    @Lazy
    private IQuestionDAO questionDAO;

    @Autowired
    @Lazy
    private IContactDAO contactDAO;

    @Autowired
    @Lazy
    private IChoiceDAO choiceDAO;

    private static final int PAGE_SIZE = 5;

    @Transactional
    public List<User> getUsers(int page) {
        int offset = (page - 1) * PAGE_SIZE;
        return userDAO.getUsers(offset, PAGE_SIZE);
    }

    @Transactional
    public void toggleUserStatus(int userId) {
        User user = userDAO.findById(userId);
        if(user != null){
            int newStatus = user.getIsActive() == 1 ? 0 : 1;
            userDAO.updateUserStatus(userId, newStatus);
        }
    }

    @Transactional
    public List<QuizHistory> getQuizHistories(int page, String category, String userEmail) {
        int offset = (page - 1) * PAGE_SIZE;
        return quizDAO.getQuizHistories(offset, PAGE_SIZE, category, userEmail);
    }

    @Transactional
    public List<Question> getQuestions(int page) {
        int offset = (page - 1) * PAGE_SIZE;
        return questionDAO.getQuestions(offset, PAGE_SIZE);
    }

    @Transactional
    public void toggleQuestionStatus(int questionId) {
        Question question = questionDAO.getQuestionById(questionId);
        if(question != null){
            int newStatus = question.getIsActive() == 1 ? 0 : 1;
            questionDAO.updateQuestionStatus(questionId, newStatus);
        }
    }

    @Transactional
    public Question getQuestionById(int questionId) {
        return questionDAO.getQuestionById(questionId);
    }

    @Transactional
    public void addQuestion(Question question, List<Choice> choices) {
        question.setChoices(choices);
        for (Choice choice : choices) {
            choice.setQuestion(question);
        }
        questionDAO.addQuestion(question);
    }

    @Transactional
    public QuestionEditForm getQuestionEditForm(int questionId) {
        Question question = questionDAO.getQuestionById(questionId);
        List<Choice> choices = choiceDAO.getChoicesByQuestionId(questionId);

        QuestionEditForm form = new QuestionEditForm();
        form.setQuestionId(question.getQuestionId());
        form.setCategoryId(question.getCategory().getCategoryId());
        form.setDescription(question.getDescription());
        if (choices != null && choices.size() >= 4) {
            form.setChoice1Id(choices.get(0).getChoiceId());
            form.setChoice1(choices.get(0).getDescription());
            form.setChoice2Id(choices.get(1).getChoiceId());
            form.setChoice2(choices.get(1).getDescription());
            form.setChoice3Id(choices.get(2).getChoiceId());
            form.setChoice3(choices.get(2).getDescription());
            form.setChoice4Id(choices.get(3).getChoiceId());
            form.setChoice4(choices.get(3).getDescription());
            if (choices.get(0).getIsCorrect() == 1) {
                form.setCorrectChoice(1);
            } else if (choices.get(1).getIsCorrect() == 1) {
                form.setCorrectChoice(2);
            } else if (choices.get(2).getIsCorrect() == 1) {
                form.setCorrectChoice(3);
            } else if (choices.get(3).getIsCorrect() == 1) {
                form.setCorrectChoice(4);
            }
        }
        return form;
    }

    @Transactional
    public void updateQuestion(QuestionEditForm form) {
        Question question = questionDAO.getQuestionById(form.getQuestionId());
        question.setQuestionId(form.getQuestionId());

        Category category = new Category();
        category.setCategoryId(form.getCategoryId());
        question.setCategory(category);

        question.setDescription(form.getDescription());
        questionDAO.updateQuestion(question);

        Choice choice1 = choiceDAO.getChoiceById(form.getChoice1Id());
        choice1.setChoiceId(form.getChoice1Id());
        choice1.setDescription(form.getChoice1());
        choice1.setIsCorrect(form.getCorrectChoice() == 1 ? 1 : 0);
        choiceDAO.updateChoice(choice1);

        Choice choice2 = choiceDAO.getChoiceById(form.getChoice2Id());
        choice2.setChoiceId(form.getChoice2Id());
        choice2.setDescription(form.getChoice2());
        choice2.setIsCorrect(form.getCorrectChoice() == 2 ? 1 : 0);
        choiceDAO.updateChoice(choice2);

        Choice choice3 = choiceDAO.getChoiceById(form.getChoice3Id());
        choice3.setChoiceId(form.getChoice3Id());
        choice3.setDescription(form.getChoice3());
        choice3.setIsCorrect(form.getCorrectChoice() == 3 ? 1 : 0);
        choiceDAO.updateChoice(choice3);

        Choice choice4 = choiceDAO.getChoiceById(form.getChoice4Id());
        choice4.setChoiceId(form.getChoice4Id());
        choice4.setDescription(form.getChoice4());
        choice4.setIsCorrect(form.getCorrectChoice() == 4 ? 1 : 0);
        choiceDAO.updateChoice(choice4);
    }

    @Transactional
    public List<Contact> getContacts(int page) {
        int offset = (page - 1) * PAGE_SIZE;
        return contactDAO.getContacts(offset, PAGE_SIZE);
    }

    @Transactional
    public Contact getContactById(int contactId) {
        return contactDAO.getContactById(contactId);
    }
}
