package ru.itis.servlet.announcement;

import ru.itis.dto.CreateAnnouncementDto;
import ru.itis.model.Announcement;
import ru.itis.model.Currency;
import ru.itis.model.Image;
import ru.itis.model.User;
import ru.itis.service.AnnouncementService;
import ru.itis.service.ImageService;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024)
@WebServlet("/announcements/my/new")
public class CreateAnnouncementServlet extends HttpServlet {
    private AnnouncementService announcementService;
    private ImageService imageService ;


    @Override
    public void init(ServletConfig config) throws ServletException {
        announcementService = (AnnouncementService) config.getServletContext().getAttribute("announcementService");
        imageService = (ImageService) config.getServletContext().getAttribute("imageService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> currencyList = new ArrayList<>();
        currencyList.add(Currency.RUB);
        currencyList.add(Currency.EUR);
        currencyList.add(Currency.USD);

        req.setAttribute("currencyList", currencyList);
        req.getRequestDispatcher(JspPaths.CREATE_ANNOUNCEMENT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Collection<Part> parts = req.getParts();

        CreateAnnouncementDto createAnnouncementDto = CreateAnnouncementDto.builder()
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Long.parseLong(req.getParameter("price")))
                .currency(req.getParameter("currency"))
                .userId(((User) req.getSession().getAttribute("user")).getId())
                .build();

        Announcement announcement = new Announcement(createAnnouncementDto);
        var announcementId = announcementService.saveAnnouncement(announcement);

        boolean noErrors = true;
        for (var part : parts) {
            if (part.getName().equals("images")) {
                Image image = imageService.uploadImage(part.getInputStream(), part.getSubmittedFileName(), part.getContentType(), part.getSize(), announcementId);

            }
            if (noErrors) resp.sendRedirect("/announcements/my");
            else {
                List<String> currencyList = new ArrayList<>();
                currencyList.add(Currency.RUB);
                currencyList.add(Currency.EUR);
                currencyList.add(Currency.USD);
                req.setAttribute("currencyList", currencyList);
                req.setAttribute("message", "image should be .png or .jpg");
                req.getRequestDispatcher(JspPaths.CREATE_ANNOUNCEMENT).forward(req, resp);
            }
        }
    }
}