package com.epam.inmemory.repo;

import com.epam.db.bean.ProductBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart {

    private Map<ProductBean, Integer> cart = new HashMap<>();

    public double getSum() {
        double sum = 0;
        for (ProductBean productBean : cart.keySet()) {
            sum += productBean.getPrice() * cart.get(productBean);
        }
        return sum;
    }

    public void putToCart(ProductBean productBean) {
        cart.put(productBean, cart.containsKey(productBean) ? (cart.get(productBean) + 1) : 1);
    }

    public void changeCount(ProductBean productBean, int newCount) {
        cart.put(productBean, newCount);
    }

    public void removeFromCart(ProductBean productBean) {
        cart.remove(productBean);
    }

    public void clearCart() {
        cart.clear();
    }

    public Map<ProductBean, Integer> getCart() {
        return new HashMap<>(cart);
    }

    public int productQuantity() {
        return cart.values().stream().reduce(Integer::sum).orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart1 = (Cart) o;
        return Objects.equals(cart, cart1.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart);
    }
}
