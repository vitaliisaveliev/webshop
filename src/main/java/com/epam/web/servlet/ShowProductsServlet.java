package com.epam.web.servlet;


import com.epam.db.bean.CategoryBean;
import com.epam.db.bean.FilterBean;
import com.epam.db.bean.ManufacturerBean;
import com.epam.db.bean.ProductBean;
import com.epam.db.entity.ProductFilter;
import com.epam.db.queryBuilder.QueryBuilder;
import com.epam.inmemory.repo.Cart;
import com.epam.service.ProductService;
import com.epam.util.Mapper;
import com.epam.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.constant.ApplicationConstants.CART;
import static com.epam.constant.ApplicationConstants.CATEGORIES;
import static com.epam.constant.ApplicationConstants.COUNT;
import static com.epam.constant.ApplicationConstants.CURRENT_PAGE;
import static com.epam.constant.ApplicationConstants.FILTER_BEAN;
import static com.epam.constant.ApplicationConstants.MANUFACTURERS;
import static com.epam.constant.ApplicationConstants.PAGES;
import static com.epam.constant.ApplicationConstants.PRODUCTS;
import static com.epam.constant.ApplicationConstants.PRODUCTS_JSP;
import static com.epam.constant.ApplicationConstants.PRODUCT_SERVICE;


@WebServlet("/showProducts")
public class ShowProductsServlet extends HttpServlet {

    private QueryBuilder queryBuilder;
    private ProductService productService;
    private List<CategoryBean> categories;
    private List<ManufacturerBean> manufacturers;
    private List<ProductBean> products;
    private int perPage;
    private int count;

    @Override
    public void init() {
        queryBuilder = new QueryBuilder();
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        categories = productService.getProductCategories();
        manufacturers = productService.getProductManufacturers();
        perPage = 5;
        count = productService.getProductCount();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        putCartToSession(request);
        products = new ArrayList<>();
        if (request.getParameter(CURRENT_PAGE) == null) {
            fillInitialProducts(request);
        } else {
            fillProductsWithInput(request);
        }
        setUpForward(request, response);
    }

    private void putCartToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(CART) == null) {
            session.setAttribute(CART, new Cart());
        }
    }

    private void fillInitialProducts(HttpServletRequest request) {
        queryBuilder.buildInitialQuery(queryBuilder);
        String query = queryBuilder.limit(perPage).build();
        products = productService.getProductsWithoutUserInput(query);
        count = getCount(null);
        setCurrentPage(request, 1);
    }

    private void fillProductsWithInput(HttpServletRequest request) {
        FilterBean filterBean = Mapper.fillFilterBean(request);
        Map<String, String> errors = Validation.validateFilter(filterBean);
        if (errors.isEmpty()) {
            ProductFilter productFilter = Mapper.createFilter(filterBean);
            List<String> parameters = new ArrayList<>();
            products = productService.getProductsWithUserInput(setUpQueryWithFilter(filterBean, productFilter, parameters), parameters);
            count = getCount(parameters);
            perPage = productFilter.getPerPage();
        }
        request.setAttribute(FILTER_BEAN, filterBean);
        setCurrentPage(request, filterBean.getCurrentPage());
    }

    private String setUpQueryWithFilter(FilterBean filterBean, ProductFilter productFilter, List<String> parameters) {
        queryBuilder.buildInitialQuery(queryBuilder);
        queryBuilder.buildQuery(queryBuilder, filterBean, productFilter, parameters);
        queryBuilder.limit(productFilter.getPerPage()).offset(productFilter.getPerPage() *
                (productFilter.getCurrentPage() - 1));
        return queryBuilder.build();
    }

    private void setCurrentPage(HttpServletRequest request, int currentPage) {
        request.setAttribute(CURRENT_PAGE, currentPage);
    }

    private void setUpForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pages = products.isEmpty() ? 0 : getPages(perPage, count);
        setRequestAttributes(request, products, count, pages);
        request.getRequestDispatcher(PRODUCTS_JSP).forward(request, response);
    }

    private int getPages(int perPage, int count) {
        int pages = count / perPage;
        if (count % perPage != 0) {
            pages++;
        }
        return pages;
    }

    private void setRequestAttributes(HttpServletRequest request, List<ProductBean> products, int count, int pages) {
        request.setAttribute(PAGES, pages);
        request.setAttribute(COUNT, count);
        request.setAttribute(CATEGORIES, categories);
        request.setAttribute(PRODUCTS, products);
        request.setAttribute(MANUFACTURERS, manufacturers);
    }

    private int getCount(List<String> parameters) {
        int count;
        count = productService.getCount(queryBuilder.getCount(), parameters);
        return count;
    }
}