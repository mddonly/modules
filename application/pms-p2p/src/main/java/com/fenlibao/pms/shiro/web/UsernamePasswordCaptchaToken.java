package com.fenlibao.pms.shiro.web;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Administrator on 2016/4/15.
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 1L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken() {
        super();
    }

    public UsernamePasswordCaptchaToken(String username, char[] password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
