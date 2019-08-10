package com.epam.captcha.provider.impl;

import com.epam.captcha.MyCaptcha;
import nl.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class FieldCaptchaProvider extends AbstractCaptchaProvider {

    public FieldCaptchaProvider(int time) {
        super(time);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = new MyCaptcha().getCaptcha();
        String key = (String) request.getServletContext().getAttribute("captchaKey");
        putCaptchaWithKey(key, captcha.getAnswer());
        return captcha;
    }

    @Override
    public boolean isValidCaptcha(HttpServletRequest request, String answer) {
        String captchaKey = request.getParameter("captcha");
        if (Objects.nonNull(captchaKey)) {
            String captchaAnswer = captchas.getOrDefault(captchaKey, "");
            captchas.remove(captchaKey);
            return checkIfNotExpiredAndCleanExpiredCaptchas(captchaKey) && captchaAnswer.equals(answer);
        }
        return false;
    }

    @Override
    public String generateCaptchaKey() {
        return generateKey();
    }
}
