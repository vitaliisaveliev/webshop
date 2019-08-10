package com.epam.captcha;

import nl.captcha.Captcha;
import nl.captcha.text.producer.NumbersAnswerProducer;

public class MyCaptcha {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    private static final int LENGTH = 6;

    public Captcha getCaptcha() {
        return new Captcha.Builder(WIDTH, HEIGHT)
                .addBorder()
                .addNoise()
                .addBackground()
                .addText(new NumbersAnswerProducer(LENGTH))
                .build();
    }
}
