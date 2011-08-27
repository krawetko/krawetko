package com.sabre.myfares.spike;

import java.util.Map;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedListener;

public class ViewManager {
    private UriFragmentUtility uri;
    private Map<String, Component> viewsByUri;
    private HorizontalSplitPanel body;

    public void init() {
        uri.addListener(new FragmentChangedListener() {
            @Override
            public void fragmentChanged(FragmentChangedEvent source) {
                String fragment = source.getUriFragmentUtility().getFragment();
                if(fragment!=null && viewsByUri.containsKey(fragment)) {
                    body.setSecondComponent(viewsByUri.get(fragment));
                }
            }
        });
    }

    public void setUri(UriFragmentUtility uri) {
        this.uri = uri;
    }

    public void setViewsByUri(Map<String, Component> viewsByUri) {
        this.viewsByUri = viewsByUri;
    }

    public void setBody(HorizontalSplitPanel body) {
        this.body = body;
    }

}
