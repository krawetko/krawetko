package com.sabre.myfares.spike.presenter.impl;

import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.presenter.ShowProductsDetailsPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.service.ShopService;
import com.sabre.myfares.spike.ui.view.domain.ProductDetailsDomainView;
import com.vaadin.ui.UriFragmentUtility;

public class ShowProductsDetailsPresenterImpl implements ShowProductsDetailsPresenter {
    private ShopService shopService;
    private ProductDetailsDomainView productDetailsDomainView;
    private UriFragmentUtility uri;

    @Override
    public void perform(Context context) {
        Long productId = (Long) context.get(ShowProductsDetailsPresenter.PRODUCT_ID);
        Product product = shopService.getProductById(productId);
        productDetailsDomainView.displayProductDetails(product);
        uri.setFragment("details");
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public void setProductDetailsDomainView(ProductDetailsDomainView productDetailsDomainView) {
        this.productDetailsDomainView = productDetailsDomainView;
    }

    public void setUri(UriFragmentUtility uri) {
        this.uri = uri;
    }
}
