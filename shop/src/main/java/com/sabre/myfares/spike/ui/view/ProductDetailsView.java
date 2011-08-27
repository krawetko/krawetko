package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.ui.components.ViewTitleLabel;
import com.sabre.myfares.spike.ui.view.domain.impl.ProductDetails;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProductDetailsView extends VerticalLayout {

    private CssLayout title;
    private ProductDetails productDetails;

    public void init() {
        initLayout();

        title = buildTitle();
        addComponent(title);

        addComponent(productDetails);
        setExpandRatio(productDetails, 1);

    }

    private CssLayout buildTitle() {
        ViewTitleLabel title = new ViewTitleLabel("Product Details");
        CssLayout titleLayout = new CssLayout();
        titleLayout.setWidth("100%");
        titleLayout.setStyleName("title-bg");
        titleLayout.addComponent(title);
        return titleLayout;
    }

    private void initLayout() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setProductDetails(ProductDetails produtDetails) {
        this.productDetails = produtDetails;
    }

}
