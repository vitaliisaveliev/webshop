package com.epam.web.servlet;

import com.epam.db.bean.OrderBean;
import com.epam.db.entity.Card;
import com.epam.db.entity.Order;
import com.epam.db.entity.User;
import com.epam.inmemory.repo.Cart;
import com.epam.service.CardService;
import com.epam.service.OrderService;
import com.epam.util.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/buy")
public class MakeOrderServlet extends HttpServlet {

    private List<OrderBean> beanList;
    private OrderService orderService;
    private Order order;

    @Override
    public void init() {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(IS_AUTHORIZED) == null) {
            response.sendRedirect(request.getContextPath() + REGISTRATION_SERVLET);
        } else {
            setOrderToSession(request);
            request.getRequestDispatcher(ORDER_JSP).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cardNumber = request.getParameter(PAY_CARD);
        if (cardNumber != null) {
            User user = (User) request.getSession().getAttribute(USER);
            CardService cardService = (CardService) getServletContext().getAttribute(CARD_SERVICE);
            cardService.addCard(new Card(Integer.parseInt(cardNumber)), user.getId());
        }
        beanList = (List<OrderBean>) request.getSession().getAttribute(ORDER);
        request.getSession().removeAttribute(ORDER);
        ((Cart) request.getSession().getAttribute(CART)).clearCart();
        order = Mapper.createOrder(request, beanList);
        orderService.createOrder(order);
        response.sendRedirect(request.getContextPath() + CATALOG_SERVLET);
    }

    private void setOrderToSession(HttpServletRequest request) {
        beanList = new ArrayList<>();
        OrderBean orderBean;
        for (int i = 1; i <= Integer.parseInt(request.getParameter(PRODUCT_COUNT)); i++) {
            orderBean = new OrderBean(Integer.parseInt(request.getParameter(ID + i)),
                    Integer.parseInt(request.getParameter(COUNT + i)),
                    Double.parseDouble(request.getParameter(PRICE + i)));
            beanList.add(orderBean);
        }
        request.getSession().setAttribute(ORDER, beanList);
    }
}
