package com.epam.captcha.provider.impl;


import com.epam.captcha.MyCaptcha;
import nl.captcha.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CookieCaptchaProvider extends AbstractCaptchaProvider {

    public CookieCaptchaProvider(int time) {
        super(time);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = new MyCaptcha().getCaptcha();
        String key = putCaptcha(captcha.getAnswer());
        Cookie cookie = new Cookie("captcha", key);
        response.addCookie(cookie);
        return captcha;
    }


    @Override
    public boolean isValidCaptcha(HttpServletRequest request, String answer) {
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)){
            for (Cookie cookie : cookies) {
                if ("captcha".equals(cookie.getName())){
                    String captchaValue = cookie.getValue();
                    String captchaAnswer = captchas.getOrDefault(captchaValue, "");
                    captchas.remove(captchaValue);
                    return checkIfNotExpiredAndCleanExpiredCaptchas(captchaValue) && captchaAnswer.equals(answer);
                }
            }
        }
        return false;
    }

    @Override
    public String generateCaptchaKey() {
        return null;
    }
}
