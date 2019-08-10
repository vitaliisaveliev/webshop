package com.epam.web.servlet;

import com.epam.db.bean.ProductBean;
import com.epam.inmemory.repo.Cart;
import com.epam.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.ApplicationConstants.CART;
import static com.epam.constant.ApplicationConstants.PRODUCT_ID;
import static com.epam.constant.ApplicationConstants.PRODUCT_SERVICE;


@WebServlet("/delete")
public class DeleteFromCartServlet extends HttpServlet {

    private static final String EMPTY = "";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean deleteAll = true;
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        if (request.getParameter(PRODUCT_ID).equals(EMPTY)) {
            cart.clearCart();
        } else {
            ProductBean productBean = productService.getProductByID(Integer.parseInt(request.getParameter(PRODUCT_ID)));
            cart.removeFromCart(productBean);
            deleteAll = false;
        }
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(cart.getSum()));
        params.add(String.valueOf(cart.productQuantity()));
        params.add(deleteAll ? TRUE : FALSE);
        String json = new Gson().toJson(params);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
