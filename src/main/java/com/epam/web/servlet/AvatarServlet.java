package com.epam.web.servlet;

import com.epam.db.entity.User;
import com.epam.util.AvatarContainer;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute(USER);
        BufferedImage image;
        try {
            image = AvatarContainer.readFile(new File(AvatarContainer.createPath(user.getEmail())));
        } catch (IOException e) {
            image = AvatarContainer.readFile(new File(AvatarContainer.createPath(DEFAULT)));
        }
        ImageIO.write(image, IMAGE_TYPE, resp.getOutputStream());
    }
}
