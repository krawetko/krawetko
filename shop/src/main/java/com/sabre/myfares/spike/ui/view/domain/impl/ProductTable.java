package com.sabre.myfares.spike.ui.view.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.sabre.myfares.spike.model.Product;
import com.sabre.myfares.spike.presenter.ShowProductsDetailsPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.ui.components.QuantitySpinner;
import com.sabre.myfares.spike.ui.view.domain.ProductTableDomainView;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class ProductTable extends Table implements ProductTableDomainView {
    private static final String CHECKBOX = "";
    private static final String QUANTITY = "Quantity";
    private static final String PRICE = "Price";
    private static final String NAME = "Name";

    private ShowProductsDetailsPresenter showProductsDetailsPresenter;

    private IndexedContainer containerDataSource = new IndexedContainer();

    public ProductTable() {
        setSizeFull();
        setSelectable(true);
        setContainerDataSource(containerDataSource);
        setupHeaders();
        setImmediate(true);
    }

    private void setupHeaders() {
        setColumnCollapsingAllowed(true);
        addContainerProperty(CHECKBOX, CheckBox.class, "");
        addContainerProperty(NAME, Button.class, "");
        addContainerProperty(PRICE, Double.class, "");
        addContainerProperty(QUANTITY, QuantitySpinner.class, "");
        setColumnExpandRatio(CHECKBOX, 1);
        setColumnExpandRatio(NAME, 8);
        setColumnExpandRatio(PRICE, 4);
        setColumnExpandRatio(QUANTITY, 7);
        setColumnAlignment(CHECKBOX, Table.ALIGN_CENTER);
        setColumnAlignment(PRICE, Table.ALIGN_CENTER);
        setColumnAlignment(QUANTITY, Table.ALIGN_CENTER);
    }

    @Override
    public void displayProducts(List<Product> products) {
        containerDataSource.removeAllItems();
        addProductsToContainer(products);
    }

    @Override
    public void filterProducts(String filter) {
        containerDataSource.removeAllContainerFilters();
        containerDataSource.addContainerFilter(new StringFilter(filter));
        requestRepaintAll();
    }

    private void addProductsToContainer(List<Product> products) {
        for (Product product : products) {
            addProductToContainer(product);
        }
    }

    private void addProductToContainer(Product product) {
        Item item = containerDataSource.addItem(product.getId());
        item.getItemProperty(CHECKBOX).setValue(new CheckBox());
        item.getItemProperty(NAME).setValue(new NameLinkedWithDetails(product.getName(), product.getId()));
        item.getItemProperty(PRICE).setValue(product.getPrice());
        item.getItemProperty(QUANTITY).setValue(new QuantitySpinner(1, 99, 1, 1));
    }

    public class StringFilter implements Filter {

        private String filter;

        public StringFilter(String filter) {
            if(StringUtils.isNotBlank(filter)) {
                this.filter = filter.toLowerCase();
            }
        }

        @Override
        public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
            if(StringUtils.isBlank(filter)) {
                return true;
            }
            String name = ((String) item.getItemProperty(NAME).getValue()).toLowerCase();
            String price = item.getItemProperty(PRICE).getValue().toString().toLowerCase();

            return name.contains(filter) || price.contains(filter);
        }

        @Override
        public boolean appliesToProperty(Object propertyId) {
            return NAME.equals(propertyId) || PRICE.equals(propertyId);
        }
    }

    @Override
    public List<Long> getCheckedProductsIds() {
        List<Long> checkedIds = new ArrayList<Long>();
        for(Object itemId : containerDataSource.getItemIds()) {
            Item item = containerDataSource.getItem(itemId);
            if(((CheckBox)item.getItemProperty(CHECKBOX).getValue()).booleanValue()) {
                checkedIds.add((Long) itemId);
            }
        }
        return checkedIds;
    }

    @Override
    public void clearCheckboxes() {
        for(Object itemId : containerDataSource.getItemIds()) {
            Item item = containerDataSource.getItem(itemId);
            ((CheckBox)item.getItemProperty(CHECKBOX).getValue()).setValue(false);
        }
    }

    public void setShowProductsDetailsPresenter(ShowProductsDetailsPresenter showProductDetailsPresenter) {
        this.showProductsDetailsPresenter = showProductDetailsPresenter;
    }

    class NameLinkedWithDetails extends Button {
        public NameLinkedWithDetails(String caption, final Long productId) {
                super(caption, new ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        Context context = new Context();
                        context.put(ShowProductsDetailsPresenter.PRODUCT_ID, productId);
                        showProductsDetailsPresenter.perform(context);
                    }
                });
                setStyleName(BaseTheme.BUTTON_LINK);
        }
    }

}
