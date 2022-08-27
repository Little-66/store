package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//            检测所有session中有没有uid 有放行 没有重定向
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if(uid==null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
