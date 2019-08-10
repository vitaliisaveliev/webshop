package com.epam.web.servlet;

import com.epam.db.bean.ProductBean;
import com.epam.inmemory.repo.Cart;
import com.epam.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.CART;
import static com.epam.constant.ApplicationConstants.PRODUCT_ID;
import static com.epam.constant.ApplicationConstants.PRODUCT_SERVICE;


@WebServlet("/cartAdd")
public class AddToCartServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        ProductBean productBean = productService.getProductByID(Integer.parseInt(request.getParameter(PRODUCT_ID)));
        cart.putToCart(productBean);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(cart.getSum()));
    }
}
