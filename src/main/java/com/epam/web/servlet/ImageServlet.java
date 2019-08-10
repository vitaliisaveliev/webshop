package com.epam.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.IMAGE_TYPE;
import static com.epam.constant.ApplicationConstants.LOGO_DIR;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File productImage = new File(LOGO_DIR + req.getParameter("source"));
        BufferedImage image = ImageIO.read(productImage);
        ImageIO.write(image, IMAGE_TYPE, resp.getOutputStream());
    }
}
