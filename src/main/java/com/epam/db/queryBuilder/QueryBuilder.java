package com.epam.db.queryBuilder;


import com.epam.db.bean.FilterBean;
import com.epam.db.entity.ProductFilter;

import java.util.List;

import static com.epam.constant.ApplicationConstants.PRODUCTS;
import static com.epam.constant.ApplicationConstants.SORT_BY_NAME;
import static com.epam.constant.ApplicationConstants.SORT_BY_PRICE_DESC;
import static com.epam.constant.SQLConstants.CATEGORY_CATEGORY_ID;
import static com.epam.constant.SQLConstants.CATEGORY_NAME;
import static com.epam.constant.SQLConstants.MANUFACTURER_MANUFACTURER_ID;
import static com.epam.constant.SQLConstants.MANUFACTURER_NAME;
import static com.epam.constant.SQLConstants.PRODUCTS_PRODUCT_CATEGORY_CATEGORY_ID;
import static com.epam.constant.SQLConstants.PRODUCTS_PRODUCT_MANUFACTURER_MANUFACTURER_ID;
import static com.epam.constant.SQLConstants.PRODUCT_CATEGORY;
import static com.epam.constant.SQLConstants.PRODUCT_CATEGORY_CATEGORY_ID;
import static com.epam.constant.SQLConstants.PRODUCT_ID;
import static com.epam.constant.SQLConstants.PRODUCT_IMAGE;
import static com.epam.constant.SQLConstants.PRODUCT_MANUFACTURER;
import static com.epam.constant.SQLConstants.PRODUCT_MANUFACTURER_MANUFACTURER_ID;
import static com.epam.constant.SQLConstants.PRODUCT_NAME;
import static com.epam.constant.SQLConstants.PRODUCT_PRICE;

public class QueryBuilder {

    private static final int SELECT_SIZE = 6;
    private static final String SELECT = "SELECT";
    private static final String COLUMN = ", ";
    private static final String GAP = " ";
    private static final String FROM = " FROM ";
    private static final String JOIN = " JOIN ";
    private static final String ON = " ON ";
    private static final String EQUALLY = " = ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String LESS = " < ?";
    private static final String MORE = " > ?";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String ASC = " ASC";
    private static final String DESC = " DESC";
    private static final String LIMIT = " LIMIT ";
    private static final String OFFSET = " OFFSET ";
    private static final String LIKE = " LIKE ?";
    private static final String SEMI_COLUMN = ";";
    private static final String COUNT = "COUNT(*)";
    private int parameterSize;
    private String count;
    private StringBuilder stringBuilder = new StringBuilder();

    public String getCount() {
        return count;
    }

    public QueryBuilder select(String... columns) {
        stringBuilder.append(SELECT);
        for (String column : columns) {
            stringBuilder.append(stringBuilder.length() > SELECT_SIZE ? COLUMN : GAP).append(column);
        }
        parameterSize = stringBuilder.length();
        return this;
    }

    public QueryBuilder from(String table) {
        stringBuilder.append(FROM).append(table);
        return this;
    }

    public QueryBuilder join(String tableToJoin, String joinColumn1, String joinColumn2) {
        stringBuilder.append(JOIN).append(tableToJoin).append(ON).append(joinColumn1)
                .append(EQUALLY).append(joinColumn2);
        return this;
    }

    public QueryBuilder where(String column, String value) {
        stringBuilder.append(stringBuilder.toString().contains(WHERE) ? AND : WHERE)
                .append(column).append(EQUALLY).append(value);
        return this;
    }

    public QueryBuilder lessOrMore(boolean isLess, String column) {
        stringBuilder.append(stringBuilder.toString().contains(WHERE) ? AND : WHERE)
                .append(column).append(isLess ? LESS : MORE);
        return this;
    }

    public QueryBuilder orderBy(String column) {
        stringBuilder.append(ORDER_BY).append(column);
        return this;
    }

    public QueryBuilder orderType(boolean asc) {
        stringBuilder.append(asc ? ASC : DESC);
        return this;
    }

    public QueryBuilder limit(int value) {
        count();
        stringBuilder.append(LIMIT).append(value);
        return this;
    }

    public QueryBuilder offset(int value) {
        stringBuilder.append(OFFSET).append(value);
        return this;
    }

    public QueryBuilder like(String column) {
        stringBuilder.append(stringBuilder.toString().contains(WHERE) ? AND : WHERE).append(column)
                .append(LIKE);
        return this;
    }

    public String build() {
        String query = stringBuilder.append(SEMI_COLUMN).toString();
        stringBuilder = new StringBuilder();
        return query;
    }

    public void count() {
        StringBuilder temp = new StringBuilder(stringBuilder);
        count = temp.replace(SELECT_SIZE + 1, parameterSize, COUNT).toString();
    }

    public void buildInitialQuery(QueryBuilder queryBuilder) {
        queryBuilder.select(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,
                PRODUCT_IMAGE, CATEGORY_NAME, MANUFACTURER_NAME)
                .from(PRODUCTS)
                .join(PRODUCT_CATEGORY, PRODUCTS_PRODUCT_CATEGORY_CATEGORY_ID,
                        PRODUCT_CATEGORY_CATEGORY_ID)
                .join(PRODUCT_MANUFACTURER, PRODUCTS_PRODUCT_MANUFACTURER_MANUFACTURER_ID,
                        PRODUCT_MANUFACTURER_MANUFACTURER_ID);
    }

    public void buildQuery(QueryBuilder queryBuilder, FilterBean filterBean, ProductFilter productFilter, List<String> parameters) {
        if (productFilter.getName() != null) {
            queryBuilder.like(PRODUCT_NAME);
            parameters.add(filterBean.getName());
        }
        if (productFilter.getPriceFrom() != 0) {
            queryBuilder.lessOrMore(false, PRODUCT_PRICE);
            parameters.add(filterBean.getPriceFrom());

        }
        if (productFilter.getPriceTo() != 0) {
            queryBuilder.lessOrMore(true, PRODUCT_PRICE);
            parameters.add(filterBean.getPriceTo());

        }
        if (productFilter.getCategory() != 0) {
            queryBuilder.where(CATEGORY_CATEGORY_ID, String.valueOf(productFilter.getCategory()));
        }
        if (productFilter.getManufacturer() != 0) {
            queryBuilder.where(MANUFACTURER_MANUFACTURER_ID, String.valueOf(productFilter.getManufacturer()));
        }
        if (productFilter.getSortValue() == SORT_BY_NAME) {
            queryBuilder.orderBy(PRODUCT_NAME);
        } else if (productFilter.getSortValue() == SORT_BY_PRICE_DESC) {
            queryBuilder.orderBy(PRODUCT_PRICE).orderType(false);
        } else {
            queryBuilder.orderBy(PRODUCT_PRICE);
        }
    }
}
