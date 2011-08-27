package com.sabre.myfares.spike.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.presenter.AddProductsShoppingCardPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.service.ShopService;
import com.sabre.myfares.spike.ui.view.domain.ProductTableDomainView;
import com.sabre.myfares.spike.ui.view.domain.ShoppingCardDomainView;

public class AddProductsShoppingCardPresenterImpl implements AddProductsShoppingCardPresenter {

    private ShoppingCardDomainView shoppingCardDomainView;
    private ProductTableDomainView productTableDomainView;
    private ShopService shopService;

    @Override
    public void perform(Context context) {
        List<Long> checkedProductsIds = productTableDomainView.getCheckedProductsIds();
        List<Product> products = new ArrayList<Product>();
        for(Long productId : checkedProductsIds) {
            Product product = shopService.getProductById(productId);
            products.add(product);
        }
        shoppingCardDomainView.addProducts(products);
        productTableDomainView.clearCheckboxes();
    }

    public void setShoppingCardDomainView(ShoppingCardDomainView shoppingCardDomainView) {
        this.shoppingCardDomainView = shoppingCardDomainView;
    }

    public void setProductTableDomainView(ProductTableDomainView productTableDomainView) {
        this.productTableDomainView = productTableDomainView;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }



}
