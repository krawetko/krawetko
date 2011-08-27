package com.sabre.myfares.spike.ui.view.domain.impl;

import java.util.List;

import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.ui.view.domain.ShoppingCardDomainView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShoppingCard extends VerticalLayout implements ShoppingCardDomainView {


    @Override
    public void addProducts(List<Product> products) {
        for (Product product : products) {
            RemovableProduct removableProduct = new RemovableProduct(product);
            addComponent(removableProduct);
            removableProduct.addListener(new ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    removeComponent((Component) event.getButton().getData());
                }
            });
        }
    }

    public class RemovableProduct extends HorizontalLayout {
        private Button removeButton = new Button("Remove");

        public RemovableProduct(Product product) {
            removeButton.setData(this);
            addComponent(new Label(product.getName()));
            addComponent(removeButton);
            setComponentAlignment(removeButton, Alignment.MIDDLE_RIGHT);
            setWidth("100%");
        }

        public void addListener(ClickListener listener) {
            removeButton.addListener(listener);
        }
    }

}
