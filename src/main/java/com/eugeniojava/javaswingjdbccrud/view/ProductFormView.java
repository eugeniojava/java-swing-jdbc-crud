package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.GenericDao;
import com.eugeniojava.javaswingjdbccrud.model.Product;
import javax.swing.JFrame;

public class ProductFormView extends GenericFormView<Product> {
    public ProductFormView(JFrame parent, GenericDao<Product> dao, GenericView<Product> view, String action,
                           Product product) {
        super(parent, dao, view, action, product);
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
    protected String getFieldNameOneCapitalized() {
        return "Name";
    }

    @Override
    protected String getFieldNameTwoCapitalized() {
        return "Price";
    }

    @Override
    protected String getFieldNameThreeCapitalized() {
        return "Quantity";
    }

    @Override
    protected void setJTextFieldsFromModel() {
        jTextFieldOne.setText(t.getName());
        jTextFieldTwo.setText(String.valueOf(t.getPrice()));
        jTextFieldThree.setText(String.valueOf(t.getQuantity()));
    }

    @Override
    protected void setModelFromJTextFields() {
        t.setName(jTextFieldOne.getText());
        t.setPrice(Double.parseDouble(jTextFieldTwo.getText()));
        t.setQuantity(Integer.parseInt(jTextFieldThree.getText()));
    }

    @Override
    protected Product instantiateModelFromJTextFields() {
        return new Product(
                jTextFieldOne.getText(),
                Double.parseDouble(jTextFieldTwo.getText()),
                Integer.parseInt(jTextFieldThree.getText())
        );
    }

    @Override
    protected boolean validateFields() {
        return validateStringField(jTextFieldOne, "name")
                && validateDoubleField(jTextFieldTwo, "price")
                && validateIntegerField(jTextFieldThree, "quantity");
    }
}
