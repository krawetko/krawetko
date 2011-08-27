package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.presenter.AddProductsShoppingCardPresenter;
import com.sabre.myfares.spike.ui.components.ViewTitleLabel;
import com.sabre.myfares.spike.ui.view.domain.impl.ProductTable;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProductView extends VerticalLayout {

    private TextField filter;
    private ProductTable productTable;
    private Button addToShoppingCard;
    private ViewTitleLabel title;
    private CssLayout titleLayout;
    private AddProductsShoppingCardPresenter addProductsShoppingCardPresenter;

    public void init() {
        initLayout();

        titleLayout = buildTitle();
        addComponent(titleLayout);

        filter = buildFilter();
        addComponent(filter);
        setComponentAlignment(filter, Alignment.TOP_RIGHT);

        addComponent(productTable);
        setExpandRatio(productTable, 1);

        addToShoppingCard = new Button("Add to Shopping Card");
        addComponent(addToShoppingCard);
        setComponentAlignment(addToShoppingCard, Alignment.MIDDLE_RIGHT);

        configureEventListeners();
    }


    private void configureEventListeners() {

        filter.addListener(new TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
                productTable.filterProducts(event.getText());
            }
        });

        addToShoppingCard.addListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                addProductsShoppingCardPresenter.perform(null);
                Notification notification = new Notification("Products successfully added to the shopping card.", Notification.TYPE_HUMANIZED_MESSAGE);
                notification.setDelayMsec(1000);
                getWindow().showNotification(notification);
            }
        });
    }

    private TextField buildFilter() {
        TextField filter = new TextField();
        filter.setInputPrompt("Filter");
        return filter;
    }

    private void initLayout() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    private CssLayout buildTitle() {
        title = new ViewTitleLabel("Products");
        CssLayout titleLayout = new CssLayout();
        titleLayout.setWidth("100%");
        titleLayout.setStyleName("title-bg");
        titleLayout.addComponent(title);
        return titleLayout;
    }

    public void setProductTable(ProductTable productTable) {
        this.productTable = productTable;
    }

    public void setAddProductsShoppingCardPresenter(AddProductsShoppingCardPresenter addProductsShoppingCardPresenter) {
        this.addProductsShoppingCardPresenter = addProductsShoppingCardPresenter;
    }

}
