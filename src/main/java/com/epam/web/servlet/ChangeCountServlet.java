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
import static com.epam.constant.ApplicationConstants.COUNT;
import static com.epam.constant.ApplicationConstants.ID;
import static com.epam.constant.ApplicationConstants.PRODUCT_SERVICE;

@WebServlet("/change")
public class ChangeCountServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        ProductBean productBean = productService.getProductByID(Integer.parseInt(request.getParameter(ID)));
        int newCount = Integer.parseInt(request.getParameter(COUNT));
        cart.changeCount(productBean, newCount);
        List<Double> params = new ArrayList<>();
        params.add(cart.getSum());
        params.add((double) cart.productQuantity());
        params.add(cart.getCart().get(productBean) * productBean.getPrice());
        String json = new Gson().toJson(params);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
