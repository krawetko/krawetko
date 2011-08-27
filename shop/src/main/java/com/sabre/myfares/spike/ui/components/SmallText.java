package com.sabre.myfares.spike.ui.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SmallText extends Label {
    public SmallText(String caption) {
        super(caption);
        setStyleName(Reindeer.LABEL_SMALL);
    }
}
