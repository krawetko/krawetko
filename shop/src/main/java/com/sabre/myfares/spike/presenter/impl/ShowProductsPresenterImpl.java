package com.sabre.myfares.spike.presenter.impl;

import java.util.List;

import com.sabre.myfares.spike.model.Category;
import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.presenter.ShowProductsPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.service.ShopService;
import com.sabre.myfares.spike.ui.view.domain.ProductTableDomainView;
import com.vaadin.ui.UriFragmentUtility;

public class ShowProductsPresenterImpl implements ShowProductsPresenter{
    private ShopService shopService;
    private ProductTableDomainView productTableDomainView;
    private UriFragmentUtility uri;

    @Override
    public void perform(Context context) {
        Object categoryId = context.get(ShowProductsPresenter.CATEGORY_ID);
        List<Product> products = getProductsForCategoryOrAllProductsIfCategoryIdIsNull(categoryId);
        productTableDomainView.displayProducts(products);
        uri.setFragment("products");
    }

    private List<Product> getProductsForCategoryOrAllProductsIfCategoryIdIsNull(Object categoryId) {
        List<Product> products;
        if (categoryId == null) {
            products = shopService.getAllProducts();
        } else {
            products = getProductsForCategoryId((Long)categoryId);
        }
        return products;
    }

    private List<Product> getProductsForCategoryId(Long categoryId) {
        Category category = shopService.getCategoryById(categoryId);
        List<Product> products = getProductsForCategory(category);
        return products;
    }

    private List<Product> getProductsForCategory(Category category) {
        List<Product> productsByCategory = shopService.getProductsByCategory(category);
        for (Category subCategory : category.getSubCategories()) {
            productsByCategory.addAll(getProductsForCategory(subCategory));
        }
        return productsByCategory;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public void setProductTableDomainView(ProductTableDomainView productTableDomainView) {
        this.productTableDomainView = productTableDomainView;
    }


    public void setUri(UriFragmentUtility uri) {
        this.uri = uri;
    }
}
