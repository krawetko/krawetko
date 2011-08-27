package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.ui.view.domain.impl.ShoppingCard;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ShoppingCardView extends Window{
    private ShoppingCard shoppingCard;

    public void init() {
        setCaption("Shopping Card");
        VerticalLayout layout = (VerticalLayout) getContent();
        layout.setWidth("100%");
        layout.addComponent(shoppingCard);
        Button close = new Button("Close", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                (getParent()).removeWindow(ShoppingCardView.this);
            }
        });
        VerticalLayout closeButtonLayout = new VerticalLayout();
        closeButtonLayout.addComponent(close);
        closeButtonLayout.setComponentAlignment(close, Alignment.BOTTOM_RIGHT);
        layout.addComponent(closeButtonLayout);
        layout.setSpacing(true);
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }
}
