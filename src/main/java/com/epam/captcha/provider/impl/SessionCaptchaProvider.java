package com.epam.captcha.provider.impl;

import com.epam.captcha.MyCaptcha;
import nl.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class SessionCaptchaProvider extends AbstractCaptchaProvider {

    public SessionCaptchaProvider(int time) {
        super(time);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Captcha captcha = new MyCaptcha().getCaptcha();
        session.setAttribute("captcha", captcha.getAnswer());
        session.setAttribute("startTime", LocalDateTime.now());
        return captcha;
    }

    @Override
    public boolean isValidCaptcha(HttpServletRequest request, String answer) {
        HttpSession session = request.getSession(true);
        String captchaAnswer = (String) session.getAttribute("captcha");
        session.removeAttribute("captcha");
        if (Objects.nonNull(captchaAnswer)) {
            LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
            LocalDateTime nowTime = LocalDateTime.now();
            session.removeAttribute("startTime");
            return Duration.between(startTime, nowTime).getSeconds() <= time && captchaAnswer.equals(answer);
        }
        return false;
    }

    @Override
    public String generateCaptchaKey() {
        return null;
    }
}
