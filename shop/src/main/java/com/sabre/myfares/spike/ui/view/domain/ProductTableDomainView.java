package com.sabre.myfares.spike.ui.view.domain;

import java.util.List;

import com.sabre.myfares.spike.model.Product;

public interface ProductTableDomainView {
    public void displayProducts(List<Product> products);
    public void filterProducts(String filter);
    public List<Long> getCheckedProductsIds();
    public void clearCheckboxes();
}
