package com.sabre.myfares.spike.ui.view.domain.impl;

import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.ui.view.domain.ProductDetailsDomainView;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProductDetails extends TabSheet implements ProductDetailsDomainView {

    private ObjectProperty<String> productProperty = new ObjectProperty<String>("");
    private ObjectProperty<String> categoryProperty = new ObjectProperty<String>("");
    private ObjectProperty<String> descriptionProperty = new ObjectProperty<String>("");
    private ObjectProperty<String> opinionProperty = new ObjectProperty<String>("Great Product");

    private VerticalLayout productTab;
    private VerticalLayout categoryTab;
    private VerticalLayout descriptionTab;
    private VerticalLayout opinionTab;


    @Override
    public void displayProductDetails(Product product) {
        productProperty.setValue(product.getName());
        categoryProperty.setValue(product.getCategory().getName());
        descriptionProperty.setValue(product.getDescription());
    }

    public ProductDetails() {

        productTab = buildProductTab();
        categoryTab = buildCategoryTab();
        descriptionTab = buildDescriptionTab();
        opinionTab = buildOpinionsTab();

        addTab(productTab, "Product", null);
        addTab(categoryTab, "Category", null);
        addTab(descriptionTab, "Description", null);
        addTab(opinionTab, "Opinions", null);

        setSizeFull();

    }

    private VerticalLayout buildProductTab() {
        VerticalLayout productTab = new VerticalLayout();
        productTab.setMargin(true);
        Label product = new Label(productProperty);
        productTab.addComponent(product);
        return productTab;
    }

    private VerticalLayout buildCategoryTab() {
        VerticalLayout categoryTab = new VerticalLayout();
        categoryTab.setMargin(true);
        Label category = new Label(categoryProperty);
        categoryTab.addComponent(category);
        return categoryTab;
    }

    private VerticalLayout buildDescriptionTab() {
        VerticalLayout descriptionTab = new VerticalLayout();
        descriptionTab.setMargin(true);
        Label description = new Label(descriptionProperty);
        descriptionTab.addComponent(description);
        return descriptionTab;
    }

    private VerticalLayout buildOpinionsTab() {
        VerticalLayout opinionsTab = new VerticalLayout();
        opinionsTab.setMargin(true);
        Label opinion = new Label(opinionProperty);
        opinionsTab.addComponent(opinion);
        return opinionsTab;
    }



}
