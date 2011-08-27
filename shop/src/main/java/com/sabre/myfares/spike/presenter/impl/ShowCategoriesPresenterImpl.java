package com.sabre.myfares.spike.presenter.impl;

import java.util.List;

import com.sabre.myfares.spike.model.Category;
import com.sabre.myfares.spike.presenter.ShowCategoriesPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.service.ShopService;
import com.sabre.myfares.spike.ui.view.domain.CategoryTreeDomainView;

public class ShowCategoriesPresenterImpl implements ShowCategoriesPresenter {
    private ShopService shopService;
    private CategoryTreeDomainView categoryTreeDomainView;

    @Override
    public void perform(Context context) {
        List<Category> categories = shopService.getAllCategories();
        categoryTreeDomainView.displayCategories(categories);
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public void setCategoryTreeDomainView(CategoryTreeDomainView categoryTreeDomainView) {
        this.categoryTreeDomainView = categoryTreeDomainView;
    }
}
