package ru.itis.filter;

import ru.itis.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession currentSession = request.getSession();

        Boolean isAuth = false;
        boolean sessionExists = currentSession != null;
        boolean isSignInPage = request.getRequestURI().equals("/signIn");
        boolean isSignUpPage = request.getRequestURI().equals("/signUp");

        boolean isHomePage = request.getRequestURI().equals("/");
        String path = request.getRequestURI();
        String username = null;

        if (sessionExists) {
            isAuth = (Boolean) currentSession.getAttribute("authenticated");
            isAuth = isAuth == null ? false : true;
        }
        if (isAuth) {
            username = (String) currentSession.getAttribute("username");
        }

        if(isHomePage){
            filterChain.doFilter(request, response);
        }
        if(isAuth && !isSignInPage || !isAuth && (isSignInPage || isSignUpPage)) {

            filterChain.doFilter(request, response);
        } else if (!isAuth && (!isHomePage  )) {

            response.sendRedirect("/signIn");
        } else {

            response.sendRedirect("/home");
        }
    }


    @Override
    public void destroy() {
    }
}

