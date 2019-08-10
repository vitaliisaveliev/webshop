package com.epam.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CaptchaTag extends SimpleTagSupport {

    private static final String START_IMG = "<img src=\"/captcha?captchaKey=";
    private static final String END_IMG = " \"/>";
    private static final String START_TYPE = "<input type=\"hidden\" name=\"captcha\" value=\"";
    private static final String END_STRING = "\"/>";
    private static final String IMG_SRC_CAPTCHA = "<img src=\"/captcha\"/>";
    /**
     * Captcha public key.
     */
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        String response;
        if (key != null) {
            response = START_IMG + key + END_IMG + System.lineSeparator()
                    + START_TYPE + key + END_STRING;
        } else {
            response = IMG_SRC_CAPTCHA;
        }
        out.println(response);
    }
}
