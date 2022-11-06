package main.java.com.eugeniojava.view;

import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Product;
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
