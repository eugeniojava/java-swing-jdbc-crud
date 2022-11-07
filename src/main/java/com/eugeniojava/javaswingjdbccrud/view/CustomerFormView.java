package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Customer;
import javax.swing.JFrame;

public class CustomerFormView extends AbstractFormView<Customer> {
    public CustomerFormView(JFrame parent, AbstractDao<Customer> dao, AbstractView<Customer> view, String action,
                            Customer customer) {
        super(parent, dao, view, action, customer);
    }

    @Override
    protected String getModelNameCapitalized() {
        return "Customer";
    }

    @Override
    protected String getModelNameUncapitalized() {
        return "customer";
    }

    @Override
    protected String getFieldNameOneCapitalized() {
        return "Name";
    }

    @Override
    protected String getFieldNameTwoCapitalized() {
        return "Age";
    }

    @Override
    protected String getFieldNameThreeCapitalized() {
        return "Segment";
    }

    @Override
    protected void setJTextFieldsFromModel() {
        jTextFieldOne.setText(t.getName());
        jTextFieldTwo.setText(String.valueOf(t.getAge()));
        jTextFieldThree.setText(t.getSegment());
    }

    @Override
    protected void setModelFromJTextFields() {
        t.setName(jTextFieldOne.getText());
        t.setAge(Integer.parseInt(jTextFieldTwo.getText()));
        t.setSegment(jTextFieldThree.getText());
    }

    @Override
    protected Customer instantiateModelFromJTextFields() {
        return new Customer(
                jTextFieldOne.getText(),
                Integer.parseInt(jTextFieldTwo.getText()),
                jTextFieldThree.getText()
        );
    }

    @Override
    protected boolean validateFields() {
        return validateStringField(jTextFieldOne, "name")
                && validateIntegerField(jTextFieldTwo, "age")
                && validateStringField(jTextFieldThree, "segment");
    }
}
