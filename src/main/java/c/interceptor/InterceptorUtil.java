/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.interceptor;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Administrator
 */
public class InterceptorUtil extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String contextPath = request.getContextPath();
            String servletPath = request.getServletPath();
            String pathInfo = request.getPathInfo();
            String queryString = request.getQueryString() == null ? "" : ("?" + request.getQueryString());
            String url = request.getRequestURI();
            String user = (String) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(contextPath + "/views/admin/login.jsp?redirectUrl=" + URLEncoder.encode(servletPath + pathInfo + queryString, "UTF-8"));
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

    }

}
