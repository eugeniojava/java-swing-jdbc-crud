package main.java.com.eugeniojava.view;

import javax.swing.JFrame;
import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Employee;

public class EmployeeView extends GenericView<Employee> {

    public EmployeeView(JFrame parent, GenericDao<Employee> dao) {
        super(parent, dao);
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
    protected void instantiateFormViewToCreate() {
        new EmployeeFormView(this, dao, this, GenericFormView.ACTION_CREATE, null);
    }

    @Override
    protected void instantiateFormViewToUpdate(Employee employee) {
        new EmployeeFormView(this, dao, this, GenericFormView.ACTION_UPDATE, employee);
    }

    @Override
    protected int getIdFromModel(Employee employee) {
        return employee.getId();
    }
}
