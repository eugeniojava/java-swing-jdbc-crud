package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Employee;
import javax.swing.JFrame;

public class EmployeeFormView extends AbstractFormView<Employee> {
    public EmployeeFormView(JFrame parent, AbstractDao<Employee> dao, AbstractView<Employee> view, String action,
                            Employee employee) {
        super(parent, dao, view, action, employee);
    }

    @Override
    protected String getModelNameCapitalized() {
        return "Employee";
    }

    @Override
    protected String getModelNameUncapitalized() {
        return "employee";
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
        return "Role";
    }

    @Override
    protected void setJTextFieldsFromModel() {
        jTextFieldOne.setText(t.getName());
        jTextFieldTwo.setText(String.valueOf(t.getAge()));
        jTextFieldThree.setText(t.getRole());
    }

    @Override
    protected void setModelFromJTextFields() {
        t.setName(jTextFieldOne.getText());
        t.setAge(Integer.parseInt(jTextFieldTwo.getText()));
        t.setRole(jTextFieldThree.getText());
    }

    @Override
    protected Employee instantiateModelFromJTextFields() {
        return new Employee(
                jTextFieldOne.getText(),
                Integer.parseInt(jTextFieldTwo.getText()),
                jTextFieldThree.getText()
        );
    }

    @Override
    protected boolean validateFields() {
        return validateStringField(jTextFieldOne, "name")
                && validateIntegerField(jTextFieldTwo, "age")
                && validateStringField(jTextFieldThree, "role");
    }
}
