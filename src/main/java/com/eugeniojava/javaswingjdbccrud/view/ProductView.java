package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.model.Product;
import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import javax.swing.JFrame;

public class ProductView extends AbstractView<Product> {
    public ProductView(JFrame parent, AbstractDao<Product> dao) {
        super(parent, dao);
    }

    @Override
    protected String getModelNameCapitalized() {
        return "Product";
    }

    @Override
    protected String getModelNameUncapitalized() {
        return "product";
    }

    @Override
    protected void instantiateFormViewToCreate() {
        new ProductFormView(this, dao, this, AbstractFormView.ACTION_CREATE, null);
    }

    @Override
    protected void instantiateFormViewToUpdate(Product product) {
        new ProductFormView(this, dao, this, AbstractFormView.ACTION_UPDATE, product);
    }

    @Override
    protected int getIdFromModel(Product product) {
        return product.getId();
    }
}
