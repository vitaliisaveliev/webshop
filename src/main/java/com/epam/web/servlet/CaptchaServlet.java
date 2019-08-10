package com.epam.web.servlet;

import com.epam.captcha.provider.CaptchaProvider;
import nl.captcha.Captcha;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.CAPTCHA_PROVIDER;
import static com.epam.constant.ApplicationConstants.IMAGE_TYPE;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaProvider captchaProvider = (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
        Captcha captcha = captchaProvider.getCaptcha(request, response);
        ImageIO.write(captcha.getImage(), IMAGE_TYPE, response.getOutputStream());
    }
}
