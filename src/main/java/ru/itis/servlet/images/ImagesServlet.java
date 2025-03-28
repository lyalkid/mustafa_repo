package ru.itis.servlet.images;

import ru.itis.model.Image;
import ru.itis.service.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/images/*")
public class ImagesServlet extends HttpServlet {

    private ImageService imageService ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        imageService = (ImageService) config.getServletContext().getAttribute("imageService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace("/images/", "");

        Image image = imageService.getImageByFilePath(imagePath);

        resp.setContentType(image.getFileType());
        resp.setContentLength(image.getSize().intValue());
        resp.setHeader("Content-Disposition", "filename=\"" + image.getOriginalFileName() + "\"");
        imageService.writeFileFromStorage(image.getId(), resp.getOutputStream());
        resp.flushBuffer();
    }
}
