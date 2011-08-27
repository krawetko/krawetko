package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.ui.components.SmallText;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class HeaderView extends HorizontalLayout {

    private static final String LOGO_TITLE = "Spike Shop";
    private static final String LOGO_TITLE_DESCIRPTION = "NextGen Spike in Vaadin framework.";

    private VerticalLayout logoLayout;
    private Embedded shoppingCard;
    private ShoppingCardView shoppingCardView;

    public HeaderView() {

        initLayout();
        buildComponents();
        addComponents();

        configureEventListeners();
    }

    private void configureEventListeners() {
        shoppingCard.addListener(new ClickListener() {

            @Override
            public void click(ClickEvent event) {
                shoppingCardView.setWidth("400px");
                shoppingCardView.setPositionX(event.getClientX() - 400);
                shoppingCardView.setPositionY(event.getClientY());
                getWindow().addWindow(shoppingCardView);
            }
        });
    }

    private void initLayout() {
        setWidth("100%");
        setMargin(true);
        setStyleName("footer");
    }

    private void addComponents() {
        addComponent(logoLayout);
        addComponent(shoppingCard);
        setComponentAlignment(shoppingCard, Alignment.TOP_RIGHT);
    }

    private void buildComponents() {
        buildLogo();
    }

    private void buildLogo() {
        logoLayout = new VerticalLayout();
        H2 title = new H2(LOGO_TITLE);
        logoLayout.addComponent(title);
        SmallText description = new SmallText(LOGO_TITLE_DESCIRPTION);
        description.setSizeUndefined();
        logoLayout.addComponent(description);

        shoppingCard = new Embedded(null, new ThemeResource("images/shoppedCard.png"));
    }

    class H2 extends Label {
        public H2(String caption) {
            super(caption);
            setSizeUndefined();
            setStyleName(Reindeer.LABEL_H2);
        }
    }

    public void setShoppingCardView(ShoppingCardView shoppingCardView) {
        this.shoppingCardView = shoppingCardView;
    }

}
