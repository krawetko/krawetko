package com.sabre.myfares.spike.ui.components;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ViewTitleLabel extends Label {

    public ViewTitleLabel(String title) {
        super("<h2>" + title + "</h2>", Label.CONTENT_XHTML);
        setStyleName("title-section");
        setWidth("100%");
    }

}
