package com.epam.captcha.provider.impl;

import com.epam.captcha.provider.CaptchaProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

public abstract class AbstractCaptchaProvider extends CaptchaProvider {

    protected Map<String, String> captchas;

    private Map<String, LocalDateTime> captchaTimes;

    public AbstractCaptchaProvider(int time) {
        super(time);
        captchas = new HashMap<>();
        captchaTimes = new TreeMap<>();
    }

    protected String putCaptcha(String captchaAnswer) {
        String key = generateKey();
        captchas.put(key, captchaAnswer);
        captchaTimes.put(key, LocalDateTime.now());
        return key;
    }

    protected void putCaptchaWithKey(String key, String captchaAnswer) {
        captchas.put(key, captchaAnswer);
        captchaTimes.put(key, LocalDateTime.now());
    }

    protected boolean checkIfNotExpiredAndCleanExpiredCaptchas(String key) {
        cleanExpiredCaptchas();
        LocalDateTime startTime = captchaTimes.get(key);
        return Objects.nonNull(startTime);
    }

    private void cleanExpiredCaptchas() {
        LocalDateTime nowTime = LocalDateTime.now();
        Iterator<Map.Entry<String, LocalDateTime>> iterator = captchaTimes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, LocalDateTime> entry = iterator.next();
            if (Duration.between(entry.getValue(), nowTime).getSeconds() > time) {
                captchas.remove(entry.getKey());
                iterator.remove();
            }
        }
    }

    protected String generateKey() {
        return UUID.randomUUID().toString();
    }
}
