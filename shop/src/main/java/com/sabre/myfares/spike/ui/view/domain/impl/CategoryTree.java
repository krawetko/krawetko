package com.sabre.myfares.spike.ui.view.domain.impl;

import java.util.Collection;
import java.util.List;

import com.sabre.myfares.spike.model.Category;
import com.sabre.myfares.spike.ui.view.domain.CategoryTreeDomainView;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class CategoryTree extends Tree implements CategoryTreeDomainView {
    private static final String PROPERTY_NAME = "Name";

    private HierarchicalContainer dataContainer;

    public CategoryTree() {
        dataContainer = buildDataContainer();
        setContainerDataSource(dataContainer);
        setImmediate(true);
    }

    private HierarchicalContainer buildDataContainer() {
        HierarchicalContainer dataContainer = new HierarchicalContainer();
        dataContainer.addContainerProperty(PROPERTY_NAME, String.class, "");
        setItemCaptionPropertyId(PROPERTY_NAME);
        return dataContainer;
    }

    @Override
    public void displayCategories(List<Category> categories) {
        dataContainer.removeAllItems();
        for (Category category : categories) {
            if (category.isRootCategory())
                addItemToContainer(dataContainer, category);
            addChildren(category, category.getSubCategories(), dataContainer);
        }
    }

    private void addChildren(Category parentCategory, List<Category> subCategories, HierarchicalContainer dataContainer) {
        if (subCategories.isEmpty()) {
            dataContainer.setChildrenAllowed(parentCategory.getId(), false);
        }
        for (Category category : subCategories) {
            addItemToContainer(dataContainer, category);
            dataContainer.setParent(category.getId(), parentCategory.getId());
            addChildren(category, category.getSubCategories(), dataContainer);
        }
    }

    private void addItemToContainer(HierarchicalContainer dataContainer, Category category) {
        Item childItem = dataContainer.addItem(category.getId());
        Property itemProperty = childItem.getItemProperty(PROPERTY_NAME);
        itemProperty.setValue(category.getName());
    }

    @Override
    public Long getSelectedCategoryId() {
        Object categoryId = getValue();
        return categoryId == null ? null : (Long) categoryId;
    }

    @Override
    public void selectCategory(Long categoryId) {
        select(categoryId);
    }

    public void expandAllNodes() {
        Collection<?> itemIds = dataContainer.getItemIds();
        for (Object itemId : itemIds) {
            if (hasChildren(itemId)) {
                expandItemsRecursively(itemId);
            }
        }
    }

}
