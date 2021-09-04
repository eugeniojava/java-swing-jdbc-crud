package main.java.com.eugeniojava.view;

import javax.swing.JFrame;
import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Customer;

public class CustomerFormView extends GenericFormView<Customer> {

    public CustomerFormView(JFrame parent, GenericDao<Customer> dao, GenericView<Customer> view, String action,
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
