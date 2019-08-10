package com.epam.web.servlet;

import com.epam.db.bean.ProductBean;
import com.epam.exception.DBException;
import com.epam.service.ProductService;
import com.epam.util.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.constant.ApplicationConstants.*;

@WebServlet("/addProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5)
public class AddProductToCatalogServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(ADD_PRODUCT_JSP).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ProductBean productBean = Mapper.fillProductBean(httpServletRequest);
        try {
            productService.addProduct(productBean);
            httpServletRequest.getRequestDispatcher(CATALOG_SERVLET).forward(httpServletRequest, httpServletResponse);
        } catch (DBException e) {
            httpServletRequest.setAttribute("error", "You should add product with unique name");
            httpServletRequest.getRequestDispatcher(ADD_PRODUCT_JSP).forward(httpServletRequest, httpServletResponse);
        }
    }
}
