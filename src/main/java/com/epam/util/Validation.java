package com.epam.util;

import com.epam.db.bean.FilterBean;
import com.epam.db.bean.UserBean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern CHECKBOX_REGEX = Pattern.compile("^on$");
    private static final Pattern NAME_REGEX = Pattern.compile("(?i)(?x)^\\p{L}{2,}$");
    private static final Pattern EMAIL_REGEX = Pattern.compile("(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("(.+){4,}");
    private static final Pattern PRICE_REGEX = Pattern.compile("^\\d+$");

    public static Map<String, String> validateUserBean(UserBean userBean) {
        Map<String, String> errors = new HashMap<>();
        if (isInvalidParameter(userBean.getName(), NAME_REGEX)) {
            errors.put("name", "Name should be valid");
        }
        if (isInvalidParameter(userBean.getSurname(), NAME_REGEX)) {
            errors.put("surname", "Surname should be valid");
        }
        if (isInvalidParameter(userBean.getEmail(), EMAIL_REGEX)) {
            errors.put("email", "Email should be valid");
        }
        if (isInvalidParameter(userBean.getPassword(), PASSWORD_REGEX)) {
            errors.put("password", "Password should be valid");
        }
        if (isInvalidPassword(userBean.getPassword(), userBean.getConfirmPassword())) {
            errors.put("confirmPassword", "Passwords not match");
        }
        if (userBean.getCountry().equals("Select country")) {
            errors.put("country", "Select country, please");
        }
        if (isInvalidCheckbox(userBean.getIsChecked())) {
            errors.put("check", "You must agree the terms");
        }
        return errors;
    }

    public static Map<String, String> validateFilter(FilterBean filterBean) {
        Map<String, String> errors = new HashMap<>();
        if (!(filterBean.getPriceTo().isEmpty() && filterBean.getPriceFrom().isEmpty())) {
            return bothPriceInvalid(filterBean, errors);
        }
        return errors;
    }

    private static Map<String, String> bothPriceInvalid(FilterBean filterBean, Map<String, String> errors) {
        if (isInvalidParameter(filterBean.getPriceFrom(), PRICE_REGEX) && isInvalidParameter(filterBean.getPriceTo(), PRICE_REGEX)) {
            errors.put("price", "invalid type of price");
        }
        return errors;
    }

    private static boolean isInvalidParameter(String parameter, Pattern regex) {
        if (parameter != null) {
            Matcher matcher = regex.matcher(parameter);
            return !matcher.find();
        }
        return true;
    }

    private static boolean isInvalidPassword(String password, String confirmedPassword) {
        if (password != null && confirmedPassword != null) {
            return !password.equals(confirmedPassword);
        }
        return true;
    }

    private static boolean isInvalidCheckbox(String parameter) {
        if (parameter != null) {
            Matcher matcher = CHECKBOX_REGEX.matcher(parameter);
            return !matcher.find();
        }
        return false;
    }
}
