package com.sabre.myfares.spike.ui.view.domain;

import java.util.List;

import com.sabre.myfares.spike.model.Category;

public interface CategoryTreeDomainView {
    public void displayCategories(List<Category> categories);
    public Long getSelectedCategoryId();
    public void selectCategory(Long categoryId);
}
