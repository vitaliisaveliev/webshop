package com.epam.db.dao.impl;

import com.epam.constant.SQLConstants;
import com.epam.db.DBManager;
import com.epam.db.bean.CategoryBean;
import com.epam.db.bean.ManufacturerBean;
import com.epam.db.bean.ProductBean;
import com.epam.db.dao.ProductDAO;
import com.epam.exception.DBException;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.SQLConstants.*;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(ProductBean productBean) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            int catId = insertCategory(con, productBean.getCategory());
            int manId = insertManufacturer(con, productBean.getManufacturer());
            insertProduct(con, productBean, catId, manId);
            con.commit();
        } catch (SQLException ex) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public int getCount(String countQuery, List<String> parameters) {
        int result = 0;
        int count = 1;
        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(countQuery)) {
            if (parameters != null) {
                for (String parameter : parameters) {
                    ps.setString(count++, parameter);
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
        }
        return result;
    }

    @Override
    public List<ProductBean> getProductsLike(String pattern) {
        String like = "%" + pattern + "%";
        List<ProductBean> products = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(SQL_GET_PRODUCTS_LIKE);
            stmt.setString(1, like);
            rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(extractProductBean(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(con, stmt, rs);
        }
        return products;
    }

    @Override
    public int getProductCount() {
        try (Connection con = DBManager.getInstance().getConnection();
             ResultSet rs = con.prepareStatement(SQL_GET_PRODUCT_COUNT).executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            con.commit();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return -1;
    }

    @Override
    public ProductBean getProductByID(int id) {
        ProductBean product = null;
        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_GET_PRODUCT_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                product = extractProductBean(rs);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
        }
        return product;
    }

    @Override
    public List<ProductBean> getAllProducts(String query) {
        List<ProductBean> products = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                products.add(extractProductBean(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(con, stmt, rs);
        }
        return products;
    }

    @Override
    public List<ProductBean> getAllProductWithFilter(String query, List<String> parameters) {
        int count = 1;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductBean> productBeans = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(query);
            for (String parameter : parameters) {
                ps.setString(count++, parameter);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                productBeans.add(extractProductBean(rs));
            }
            con.commit();
        } catch (SQLException e) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(e.getMessage());
        } finally {
            DbUtils.closeQuietly(con, ps, rs);
        }
        return productBeans;
    }

    @Override
    public List<CategoryBean> getAllCategories() {
        List<CategoryBean> categories = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_ALL_CATEGORIES);
            while (rs.next()) {
                categories.add(extractCategory(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(con, stmt, rs);
        }
        return categories;
    }

    @Override
    public List<ManufacturerBean> getAllManufacturers() {
        List<ManufacturerBean> manufacturers = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_ALL_MANUFACTURERS);
            while (rs.next()) {
                manufacturers.add(extractManufacturer(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DbUtils.rollbackAndCloseQuietly(con);
            throw new DBException(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(con, stmt, rs);
        }
        return manufacturers;
    }

    private void insertProduct(Connection con, ProductBean productBean, int catId, int manId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int k = 1;
            ps = con.prepareStatement(SQL_ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(k++, productBean.getName());
            ps.setDouble(k++, productBean.getPrice());
            ps.setString(k++, productBean.getImg());
            ps.setString(k++, String.valueOf(catId));
            ps.setString(k, String.valueOf(manId));
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int productId = rs.getInt(1);
                    productBean.setId(productId);
                }
            }
            con.commit();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
        }
    }

    private int insertCategory(Connection con, String category) {
        List<CategoryBean> categories = getAllCategories();
        if (categories.stream().noneMatch(c -> c.getName().equals(category))) {
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setName(category);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(SQL_ADD_CATEGORY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category);
                if (ps.executeUpdate() > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int categoryId = rs.getInt(1);
                        categoryBean.setId(categoryId);
                        return categoryId;
                    }
                }
                con.commit();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
            }
        }
        return categories.stream()
                .filter(c -> c.getName().equals(category))
                .findAny()
                .get()
                .getId();
    }

    private int insertManufacturer(Connection con, String manufacturer) {
        List<ManufacturerBean> manufacturers = getAllManufacturers();
        if (manufacturers.stream().noneMatch((m -> m.getCountry().equals(manufacturer)))) {
            ManufacturerBean manufacturerBean = new ManufacturerBean();
            manufacturerBean.setCountry(manufacturer);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(SQL_ADD_MANUFACTURER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, manufacturer);
                if (ps.executeUpdate() > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int manufacturerId = rs.getInt(1);
                        manufacturerBean.setId(manufacturerId);
                        return manufacturerId;
                    }
                }
                con.commit();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
            }
        }
        return manufacturers.stream()
                .filter(m -> m.getCountry().equals(manufacturer))
                .findAny()
                .get()
                .getId();
    }

    private ProductBean extractProductBean(ResultSet resultSet) throws SQLException {
        ProductBean productBean = new ProductBean();
        int count = 1;
        productBean.setId(resultSet.getInt(count++));
        productBean.setName(resultSet.getString(count++));
        productBean.setPrice(resultSet.getInt(count++));
        productBean.setImg(resultSet.getString(count++));
        productBean.setCategory(resultSet.getString(count++));
        productBean.setManufacturer(resultSet.getString(count));
        return productBean;
    }

    private ManufacturerBean extractManufacturer(ResultSet rs) throws SQLException {
        ManufacturerBean manufacturer = new ManufacturerBean();
        manufacturer.setId(Integer.parseInt(rs.getString(SQLConstants.ID)));
        manufacturer.setCountry(rs.getString(SQLConstants.COUNTRY));
        return manufacturer;
    }

    private CategoryBean extractCategory(ResultSet rs) throws SQLException {
        CategoryBean category = new CategoryBean();
        category.setId(Integer.parseInt(rs.getString(SQLConstants.ID)));
        category.setName(rs.getString(SQLConstants.NAME));
        return category;
    }
}
