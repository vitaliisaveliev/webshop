package com.epam.util;

import com.epam.constant.ApplicationConstants;
import com.epam.db.bean.FilterBean;
import com.epam.db.bean.OrderBean;
import com.epam.db.bean.ProductBean;
import com.epam.db.bean.UserBean;
import com.epam.db.entity.Order;
import com.epam.db.entity.ProductFilter;
import com.epam.db.entity.User;
import com.epam.exception.NoImageException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.epam.constant.ApplicationConstants.*;
import static com.epam.constant.SQLConstants.COUNTRY;

public class Mapper {

    public static UserBean fillUserBean(HttpServletRequest request) {
        UserBean user = new UserBean();
        user.setName(request.getParameter(NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setPassword(request.getParameter(PASSWORD));
        user.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
        user.setCountry(request.getParameter(COUNTRY));
        user.setIsChecked(request.getParameter(CHECK));
        return user;
    }

    public static User convertToUser(UserBean userBean) {
        User user = new User();
        user.setName(userBean.getName());
        user.setSurname(userBean.getSurname());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        user.setCountry(userBean.getCountry());
        return user;
    }

    public static FilterBean fillFilterBean(HttpServletRequest req) {
        FilterBean filterBean = new FilterBean();
        filterBean.setName(req.getParameter(ApplicationConstants.FILTER_NAME));
        filterBean.setPriceFrom(req.getParameter(ApplicationConstants.PRICE_FROM));
        filterBean.setPriceTo(req.getParameter(ApplicationConstants.PRICE_TO));
        filterBean.setCategory(req.getParameter(ApplicationConstants.CATEGORY_ID));
        filterBean.setManufacturer(req.getParameter(ApplicationConstants.MANUFACTURER_ID));
        filterBean.setPerPage(Integer.parseInt(req.getParameter(ApplicationConstants.PRODUCT_QUANTITY)));
        if (req.getParameter(ApplicationConstants.CURRENT_PAGE) == null) {
            filterBean.setCurrentPage(1);
        } else {
            filterBean.setCurrentPage(Integer.parseInt(req.getParameter(ApplicationConstants.CURRENT_PAGE)));
        }
        filterBean.setSortValue(Integer.parseInt(req.getParameter(ApplicationConstants.SORT_PRODUCT)));
        return filterBean;
    }

    public static ProductFilter createFilter(FilterBean filterBean) {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setName(filterBean.getName());
        productFilter.setPriceFrom(Integer.parseInt(filterBean.getPriceFrom()));
        productFilter.setPriceTo(Integer.parseInt(filterBean.getPriceTo()));
        productFilter.setCategory(Integer.parseInt(filterBean.getCategory()));
        productFilter.setManufacturer(Integer.parseInt(filterBean.getManufacturer()));
        productFilter.setCurrentPage(filterBean.getCurrentPage());
        productFilter.setPerPage(filterBean.getPerPage());
        productFilter.setSortValue(filterBean.getSortValue());
        return productFilter;
    }

    public static Order createOrder(HttpServletRequest request, List<OrderBean> beanList) {
        Order order = new Order();
        order.setDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setDeliveryType(request.getParameter(DELIVERY));
        order.setOrderedProducts(beanList);
        order.setPaymentType(request.getParameter(PAYMENT));
        order.setStatus(STATUS);
        order.setStatementDescription(ORDER_STATEMENT);
        order.setUserID(((User) request.getSession().getAttribute(USER)).getId());
        return order;
    }

    public static ProductBean fillProductBean(HttpServletRequest httpServletRequest) {
        ProductBean productBean = new ProductBean();
        productBean.setName(httpServletRequest.getParameter(NAME));
        productBean.setPrice(Double.parseDouble(httpServletRequest.getParameter(PRICE)));
        productBean.setImg(saveProductLogo(productBean, httpServletRequest));
        productBean.setCategory(httpServletRequest.getParameter(CATEGORY));
        productBean.setManufacturer(httpServletRequest.getParameter(MANUFACTURER));
        return productBean;
    }

    private static String saveProductLogo(ProductBean productBean, HttpServletRequest request) {
        String img;
        Part image;
        try {
            image = request.getPart(PICTURE);
            img = AvatarContainer.save(image, productBean);
        } catch (ServletException | IOException e) {
            throw new NoImageException("Failed to save product logo");
        }
        System.out.println(img);
        return img;
    }
}
