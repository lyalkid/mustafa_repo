package ru.itis.fileter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import ru.itis.dto.UserDto;
import ru.itis.model.User;
import ru.itis.service.UserService;

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
        boolean isLoginPage = request.getRequestURI().equals("/signIn");
        boolean isSignUpPage = request.getRequestURI().equals("/signUp");
        boolean isWelcomePage = request.getRequestURI().equals("/");
        String path = request.getRequestURI();
        String username = null;

        if (sessionExists) {
            isAuth = (Boolean) currentSession.getAttribute("authenticated");
            isAuth = isAuth == null ? false : true;
        }
        if (isAuth) {
            username = (String) currentSession.getAttribute("username");
        }

        if(isWelcomePage){
            filterChain.doFilter(request, response);
        }
        if(isAuth && !isLoginPage || !isAuth && (isLoginPage || isSignUpPage)) {

            filterChain.doFilter(request, response);
        } else if (!isAuth && (!isWelcomePage  )) {

            response.sendRedirect("/signIn");
        } else {

            response.sendRedirect("/");
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
