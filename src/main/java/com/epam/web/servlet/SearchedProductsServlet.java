package com.epam.web.servlet;

import com.epam.db.bean.ProductBean;
import com.epam.inmemory.repo.Cart;
import com.epam.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/searchedProducts")
public class SearchedProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        putCartToSession(httpServletRequest);
        String pattern = httpServletRequest.getParameter(PATTERN);
        ProductService productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        List<ProductBean> products = productService.getProductsLike(pattern);
        httpServletRequest.setAttribute(PRODUCTS, products);
        httpServletRequest.getRequestDispatcher(SEARCHED_PRODUCTS_JSP).forward(httpServletRequest, httpServletResponse);
    }

    private void putCartToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(CART) == null) {
            session.setAttribute(CART, new Cart());
        }
    }
}
