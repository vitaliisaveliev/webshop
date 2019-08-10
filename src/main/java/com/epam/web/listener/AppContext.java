package com.epam.web.listener;

import com.epam.captcha.CaptchaContainer;
import com.epam.captcha.provider.CaptchaProvider;
import com.epam.db.dao.impl.CardDAOImpl;
import com.epam.db.dao.impl.OrderDAOImpl;
import com.epam.db.dao.impl.ProductDAOImpl;
import com.epam.db.dao.impl.UserDAOImpl;
import com.epam.service.CardService;
import com.epam.service.OrderService;
import com.epam.service.ProductService;
import com.epam.service.UserService;
import com.epam.service.impl.CardServiceImpl;
import com.epam.service.impl.OrderServiceImpl;
import com.epam.service.impl.ProductServiceImpl;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.epam.constant.ApplicationConstants.*;

public class AppContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        initCaptchaProviders(context);
        initUserService(context);
        initProductService(context);
        initOrderService(context);
        initCardService(context);
    }

    private void initCardService(ServletContext context) {
        CardService cardService = new CardServiceImpl(new CardDAOImpl());
        context.setAttribute(CARD_SERVICE, cardService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context was destroyed");
    }

    private void initOrderService(ServletContext context) {
        OrderService orderService = new OrderServiceImpl(new OrderDAOImpl());
        context.setAttribute(ORDER_SERVICE, orderService);
    }

    private void initProductService(ServletContext context) {
        ProductService productService = new ProductServiceImpl(new ProductDAOImpl());
        context.setAttribute(PRODUCT_SERVICE, productService);
    }

    private void initUserService(ServletContext context) {
        UserService userService = new UserServiceImpl(new UserDAOImpl());
        context.setAttribute(USER_SERVICE, userService);
    }

    private void initCaptchaProviders(ServletContext context) {
        String captchaMode = context.getInitParameter(CAPTCHA_MODE);
        CaptchaContainer captchaContainer = new CaptchaContainer();
        String expirationTime = context.getInitParameter(EXPIRATION_TIME);
        if (expirationTime != null) {
            try {
                int time = Integer.parseInt(expirationTime);
                captchaContainer.setExpirationTime(time);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        CaptchaProvider captchaProvider = captchaContainer.getCaptchaProvider(captchaMode);
        context.setAttribute(CAPTCHA_PROVIDER, captchaProvider);
    }
}
