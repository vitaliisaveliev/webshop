package com.epam.web.servlet;

import com.epam.captcha.provider.CaptchaProvider;
import com.epam.db.bean.UserBean;
import com.epam.db.entity.User;
import com.epam.exception.DBException;
import com.epam.exception.NoImageException;
import com.epam.service.UserService;
import com.epam.util.AvatarContainer;
import com.epam.util.Mapper;
import com.epam.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5)
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        CaptchaProvider captchaProvider = (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
        httpServletRequest.getServletContext().setAttribute(CAPTCHA_KEY, captchaProvider.generateCaptchaKey());
        httpServletRequest.setAttribute(USER, httpServletRequest.getSession().getAttribute(USER));
        httpServletRequest.setAttribute(ERRORS, httpServletRequest.getSession().getAttribute(ERRORS));
        httpServletRequest.getRequestDispatcher(REGISTRATION_JSP).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String redirect = REGISTRATION_SERVLET;
        UserService service = (UserService) getServletContext().getAttribute(USER_SERVICE);
        UserBean userBean = Mapper.fillUserBean(httpServletRequest);
        User user = null;
        Map<String, String> errors = Validation.validateUserBean(userBean);
        checkCaptcha(errors, httpServletRequest);
        HttpSession session = httpServletRequest.getSession(true);
        clearSession(session);
        if (errors.isEmpty()) {
            try {
                user = Mapper.convertToUser(userBean);
                saveAvatar(user, httpServletRequest);
                service.addUser(user);
                redirect = INDEX_JSP;
            } catch (DBException e) {
                errors.put(DUPLICATE_USER, "User with this email is already exist");
            }
        }
        if (redirect.equals(REGISTRATION_SERVLET)) {
            session.setAttribute(ERRORS, errors);
            session.setAttribute(USER, userBean);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + redirect);
    }

    private static void clearSession(HttpSession session) {
        session.removeAttribute(ERRORS);
        session.removeAttribute(USER);
    }

    private void checkCaptcha(Map<String, String> errors, HttpServletRequest request) {
        CaptchaProvider captchaProvider = (CaptchaProvider) getServletContext().getAttribute(CAPTCHA_PROVIDER);
        String captchaAnswer = request.getParameter(CAPTCHA_ANSWER);
        if (!captchaProvider.isValidCaptcha(request, captchaAnswer)) {
            errors.put(CAPTCHA_ANSWER, "Value of captcha is invalid or sign up time is out");
        }
    }

    private void saveAvatar(User user, HttpServletRequest request) throws IOException {
        Part image;
        try {
            image = request.getPart(PICTURE);
            AvatarContainer.save(image, user.getEmail());
        } catch (ServletException e) {
            throw new NoImageException("Failed to save profile picture");
        }
    }
}
