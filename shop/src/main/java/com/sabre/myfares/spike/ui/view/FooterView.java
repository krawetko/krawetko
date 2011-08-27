package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.ui.components.SmallText;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class FooterView extends HorizontalLayout{

    private final String FOOTER_TITLE = "© 2011 Sabre Inc. All rights reserved.";
    private SmallText footerTitle;


    public FooterView() {
        initLayout();
        buildComponents();
        addComponents();
    }


    private void addComponents() {
        addComponent(footerTitle);
        setComponentAlignment(footerTitle, Alignment.MIDDLE_RIGHT);
    }


    private void buildComponents() {
        footerTitle = new SmallText(FOOTER_TITLE);
        footerTitle.setSizeUndefined();
    }


    private void initLayout() {
        setMargin(true);
        setWidth("100%");
        setStyleName("footer");
    }



}
