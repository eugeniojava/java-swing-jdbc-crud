package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.model.Product;
import com.eugeniojava.javaswingjdbccrud.dao.GenericDao;
import javax.swing.JFrame;

public class ProductView extends GenericView<Product> {
    public ProductView(JFrame parent, GenericDao<Product> dao) {
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
        new ProductFormView(this, dao, this, GenericFormView.ACTION_CREATE, null);
    }

    @Override
    protected void instantiateFormViewToUpdate(Product product) {
        new ProductFormView(this, dao, this, GenericFormView.ACTION_UPDATE, product);
    }

    @Override
    protected int getIdFromModel(Product product) {
        return product.getId();
    }
}
