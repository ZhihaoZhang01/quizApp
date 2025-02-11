package com.zhihao.quizapp.aop;

import com.zhihao.quizapp.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Zhihao Zhang
 * @description TODO
 * @date 2025-02-02 6:30 PM
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || user.getIsAdmin() != 1){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}