package com.sabre.myfares.spike.ui.view;

import com.sabre.myfares.spike.presenter.ShowCategoriesPresenter;
import com.sabre.myfares.spike.presenter.ShowProductsPresenter;
import com.sabre.myfares.spike.presenter.context.Context;
import com.sabre.myfares.spike.ui.view.domain.impl.CategoryTree;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public class CategoryView extends CssLayout {
    private ShowProductsPresenter showProductsPresenter;
    private ShowCategoriesPresenter showCategoriesPresenter;

    private CategoryTree categoryTree;

    public void init() {
        setMargin(true);

        addComponent(categoryTree);

        configureEventListeners();

        showCategoriesPresenter.perform(null);
        categoryTree.expandAllNodes();
    }

    private void configureEventListeners() {
        categoryTree.addListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Context context = new Context();
                context.put(ShowProductsPresenter.CATEGORY_ID, categoryTree.getValue());
                showProductsPresenter.perform(context);
            }
        });
    }

    public void setShowProductsPresenter(ShowProductsPresenter showProductsPresenter) {
        this.showProductsPresenter = showProductsPresenter;
    }

    public void setShowCategoriesPresenter(ShowCategoriesPresenter showCategoriesPresenter) {
        this.showCategoriesPresenter = showCategoriesPresenter;
    }

    public void setCategoryTree(CategoryTree categoryTree) {
        this.categoryTree = categoryTree;
    }

}
