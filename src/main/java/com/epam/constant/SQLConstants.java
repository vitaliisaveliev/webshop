package com.epam.constant;

public final class SQLConstants {


    private SQLConstants() {
    }

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String COUNTRY = "country";

    public static final String CATEGORY_CATEGORY_ID = "products.cat_id";
    public static final String MANUFACTURER_MANUFACTURER_ID = "products.man_id";
    public static final String PRODUCT_ID = "products.id";
    public static final String PRODUCT_NAME = "products.`name`";
    public static final String PRODUCT_PRICE = "products.price";
    public static final String PRODUCT_IMAGE = "products.image";
    public static final String CATEGORY_NAME = "categories.`name`";
    public static final String MANUFACTURER_NAME = "manufacture.country ";
    public static final String PRODUCT_CATEGORY = "categories";
    public static final String PRODUCTS_PRODUCT_CATEGORY_CATEGORY_ID = "products.cat_id";
    public static final String PRODUCT_CATEGORY_CATEGORY_ID = "categories.id";
    public static final String PRODUCT_MANUFACTURER = "manufacture";
    public static final String PRODUCTS_PRODUCT_MANUFACTURER_MANUFACTURER_ID = "products.man_id";
    public static final String PRODUCT_MANUFACTURER_MANUFACTURER_ID = "manufacture.id";

    public static final String SQL_GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    public static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=? AND password=?";
    public static final String SQL_GET_PRODUCT_COUNT = "SELECT count(*) from products";
    public static final String SQL_GET_ALL_MANUFACTURERS = "SELECT * FROM manufacture";
    public static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM categories";
    public static final String SQL_ADD_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    public static final String SQL_INSERT_ORDER = "INSERT INTO orders VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_INSERT_ORDER_PRODUCT = "INSERT INTO products_in_order VALUES(?, ?, ?)";
    public static final String SQL_ADD_PRODUCT = "INSERT INTO products VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    public static final String SQL_ADD_CATEGORY = "INSERT INTO categories VALUES(DEFAULT, ?)";
    public static final String SQL_ADD_MANUFACTURER = "INSERT INTO manufacturers VALUES(DEFAULT, ?)";
    public static final String SQL_GET_PRODUCTS_LIKE = "SELECT products.id, products.`name`, products.price, products.image, categories.`name`, manufacture.country \n" +
            "FROM products JOIN categories ON products.cat_id=categories.id JOIN manufacture ON products.man_id=manufacture.id\n" +
            "WHERE products.`name` LIKE ?";
    public static final String SQL_INSERT_CARD = "INSERT INTO payment_cards values(?)";
    public static final String SQL_INSERT_CARDS_ON_USER = "INSERT INTO cards_on_user values(?,?)";

}
