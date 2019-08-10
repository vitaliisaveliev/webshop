package com.epam.captcha;



import com.epam.captcha.provider.CaptchaProvider;
import com.epam.captcha.provider.impl.CookieCaptchaProvider;
import com.epam.captcha.provider.impl.FieldCaptchaProvider;
import com.epam.captcha.provider.impl.SessionCaptchaProvider;

import java.util.HashMap;
import java.util.Map;

public class CaptchaContainer {

    private static final int DEFAULT_EXPIRATION_TIME = 120;
    private int expirationTime;
    private Map<String, CaptchaProvider> captchaProviders;
    private CaptchaProvider defaultCaptchaProvider;


    public CaptchaContainer() {
        captchaProviders = new HashMap<>();
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public CaptchaProvider getCaptchaProvider(String key) {
        if (captchaProviders.isEmpty()) {
            initMap();
        }
        return captchaProviders.getOrDefault(key, defaultCaptchaProvider);
    }

    private void initMap() {
        int initTime = (expirationTime <= 0 ? DEFAULT_EXPIRATION_TIME : expirationTime);
        defaultCaptchaProvider = new SessionCaptchaProvider(initTime);
        captchaProviders.put("Session", defaultCaptchaProvider);
        captchaProviders.put("Cookie", new CookieCaptchaProvider(initTime));
        captchaProviders.put("Field", new FieldCaptchaProvider(initTime));
    }

}
