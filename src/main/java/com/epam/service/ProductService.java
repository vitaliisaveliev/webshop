package com.epam.service;

import com.epam.db.bean.CategoryBean;
import com.epam.db.bean.ManufacturerBean;
import com.epam.db.bean.ProductBean;

import java.util.List;

public interface ProductService {

    int getCount(String count, List<String> parameters);

    List<ProductBean> getProductsLike(String pattern);

    ProductBean getProductByID(int id);

    void addProduct(ProductBean productBean);

    List<ProductBean> getProductsWithUserInput(String query, List<String> parameters);

    List<ProductBean> getProductsWithoutUserInput(String query);

    int getProductCount();

    List<CategoryBean> getProductCategories();

    List<ManufacturerBean> getProductManufacturers();
}
