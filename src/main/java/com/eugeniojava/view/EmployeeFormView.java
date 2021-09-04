package main.java.com.eugeniojava.view;

import javax.swing.JFrame;
import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Employee;

public class EmployeeFormView extends GenericFormView<Employee> {

    public EmployeeFormView(JFrame parent, GenericDao<Employee> dao, GenericView<Employee> view, String action,
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
