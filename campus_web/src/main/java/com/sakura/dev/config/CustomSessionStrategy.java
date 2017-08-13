package com.sakura.dev.config;

import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rc452 on 2017/7/30.
 */
public class CustomSessionStrategy implements HttpSessionStrategy {
    private HttpSessionStrategy browser;
    private HttpSessionStrategy api;

    public CustomSessionStrategy() {
        this.browser = new CookieHttpSessionStrategy();
        this.api = new HeaderHttpSessionStrategy();

        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("sessionId");
        ((CookieHttpSessionStrategy) this.browser).setCookieSerializer(serializer);

        ((HeaderHttpSessionStrategy) this.api).setHeaderName("sessionId");
    }

    public CustomSessionStrategy(HttpSessionStrategy browser, HttpSessionStrategy api) {
        this.browser = browser;
        this.api = api;
    }

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        String session = browser.getRequestedSessionId(request);
        if (session == null || session.equals("")) {
            session = api.getRequestedSessionId(request);
        }
        return session;
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        browser.onNewSession(session, request, response);
        api.onNewSession(session, request, response);
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        browser.onInvalidateSession(request, response);
        api.onInvalidateSession(request, response);
    }

}