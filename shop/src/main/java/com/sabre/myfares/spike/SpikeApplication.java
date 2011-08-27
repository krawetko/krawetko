package com.sabre.myfares.spike;

import com.sabre.myfares.spike.ui.view.CategoryView;
import com.sabre.myfares.spike.ui.view.FooterView;
import com.sabre.myfares.spike.ui.view.HeaderView;
import com.sabre.myfares.spike.ui.view.ProductView;
import com.vaadin.Application;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SpikeApplication extends Application {

    private Window main = new Window("Spike Shop");
    private VerticalLayout mainLayout = (VerticalLayout) main.getContent();
    private HeaderView header;
    private HorizontalSplitPanel body;
    private CategoryView category;
    private ProductView product;
    private FooterView footer;

    UriFragmentUtility uri;

    @Override
    public void init() {
        main.addComponent(uri);
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        setTheme("spike");
        setMainWindow(main);

        mainLayout.addComponent(header);

        HorizontalSplitPanel body = buildBody();
        mainLayout.addComponent(body);
        mainLayout.setExpandRatio(body, 1.0f);

        mainLayout.addComponent(footer);
    }

    private HorizontalSplitPanel buildBody() {
        body.setMargin(false, true, true, true);
        body.setStyleName("main-split");
        body.setSplitPosition(15);
        body.setFirstComponent(category);
        body.setSecondComponent(product);
        return body;
    }

    public void setHeader(HeaderView header) {
        this.header = header;
    }

    public void setFooter(FooterView footer) {
        this.footer = footer;
    }

    public void setCategory(CategoryView category) {
        this.category = category;
    }

    public void setProduct(ProductView product) {
        this.product = product;
    }

    public void setBody(HorizontalSplitPanel body) {
        this.body = body;
    }

    public void setUri(UriFragmentUtility uri) {
        this.uri = uri;
    }
}
