package com.epam.service.impl;

import com.epam.db.bean.CategoryBean;
import com.epam.db.bean.ManufacturerBean;
import com.epam.db.bean.ProductBean;
import com.epam.db.dao.ProductDAO;
import com.epam.db.dao.impl.ProductDAOImpl;
import com.epam.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO dao;

    public ProductServiceImpl(ProductDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    public int getCount(String count, List<String> parameters) {
        return dao.getCount(count, parameters);
    }

    @Override
    public List<ProductBean> getProductsLike(String pattern) {
        return dao.getProductsLike(pattern);
    }

    @Override
    public ProductBean getProductByID(int id) {
        return dao.getProductByID(id);
    }

    @Override
    public void addProduct(ProductBean productBean) {
        dao.addProduct(productBean);
    }

    @Override
    public List<ProductBean> getProductsWithUserInput(String query, List<String> parameters) {
        return dao.getAllProductWithFilter(query, parameters);
    }

    @Override
    public List<ProductBean> getProductsWithoutUserInput(String query) {
        return dao.getAllProducts(query);
    }

    @Override
    public int getProductCount() {
        return dao.getProductCount();
    }

    @Override
    public List<CategoryBean> getProductCategories() {
        return dao.getAllCategories();
    }

    @Override
    public List<ManufacturerBean> getProductManufacturers() {
        return dao.getAllManufacturers();
    }
}
