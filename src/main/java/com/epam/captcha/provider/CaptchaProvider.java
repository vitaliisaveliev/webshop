package com.epam.captcha.provider;

import nl.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CaptchaProvider {

    protected int time;

    public CaptchaProvider(int time) {
        this.time = time;
    }

    public abstract Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response);

    public abstract boolean isValidCaptcha(HttpServletRequest request, String answer);

    public abstract String generateCaptchaKey();

}
