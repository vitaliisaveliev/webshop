package com.epam.db.dao;

import com.epam.db.bean.CategoryBean;
import com.epam.db.bean.ManufacturerBean;
import com.epam.db.bean.ProductBean;

import java.util.List;

public interface ProductDAO {

    int getProductCount();

    List<ManufacturerBean> getAllManufacturers();

    List<CategoryBean> getAllCategories();

    ProductBean getProductByID(int id);

    List<ProductBean> getAllProductWithFilter(String query, List<String> parameters);

    List<ProductBean> getAllProducts(String query);

    void addProduct(ProductBean productBean);


    int getCount(String countQuery, List<String> parameters);

    List<ProductBean> getProductsLike(String pattern);
}
