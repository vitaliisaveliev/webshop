package com.epam.db.bean;


public class FilterBean {

    private static final String EMPTY = "";
    private static final String ZERO = "0";
    private static final String MAX_VALUE = "999999";

    private String name;
    private String priceFrom;
    private String priceTo;
    private String category;
    private String manufacturer;
    private int perPage;
    private int currentPage;
    private int sortValue;

    public FilterBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.equals(EMPTY) ? null : name;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom.equals(EMPTY) ? ZERO : priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo.equals(EMPTY) ? MAX_VALUE : priceTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? ZERO : category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? ZERO : manufacturer;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSortValue() {
        return sortValue;
    }

    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }
}