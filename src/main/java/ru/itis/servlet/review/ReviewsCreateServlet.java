package ru.itis.servlet.review;

import ru.itis.dto.CreateReviewDto;
import ru.itis.model.Review;
import ru.itis.model.User;
import ru.itis.service.ReviewService;
import ru.itis.service.UserService;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reviews/create")
public class ReviewsCreateServlet extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        reviewService = (ReviewService) config.getServletContext().getAttribute("reviewService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long announcementId = Long.parseLong(req.getParameter("announcementId"));
        req.setAttribute("announcementId", announcementId);
        req.getRequestDispatcher(JspPaths.REVIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Long announcementId = Long.parseLong(req.getParameter("announcementId"));
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        CreateReviewDto createReviewDto = CreateReviewDto.builder()
                .rate(req.getParameter("rate"))
                .user(user)
                .comment(req.getParameter("comment"))
                .announcementId(announcementId)
                .build();
        if (reviewService.getReviewByUser(createReviewDto.getUser().getId(), createReviewDto.getAnnouncementId()) == null) {
            reviewService.addReview(new Review(createReviewDto));
        }
        resp.sendRedirect(req.getContextPath() + "/announcements/" + announcementId);
    }
}
