package com.epam.web.servlet;

import com.epam.inmemory.repo.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.CART;
import static com.epam.constant.ApplicationConstants.CART_JSP;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        request.setAttribute(CART, cart.getCart());
        request.setAttribute("sum", cart.getSum());
        request.getRequestDispatcher(CART_JSP).forward(request, response);
    }
}
