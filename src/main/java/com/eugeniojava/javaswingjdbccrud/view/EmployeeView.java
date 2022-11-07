package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import com.eugeniojava.javaswingjdbccrud.model.Employee;
import javax.swing.JFrame;

public class EmployeeView extends AbstractView<Employee> {
    public EmployeeView(JFrame parent, AbstractDao<Employee> dao) {
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
        new EmployeeFormView(this, dao, this, AbstractFormView.ACTION_CREATE, null);
    }

    @Override
    protected void instantiateFormViewToUpdate(Employee employee) {
        new EmployeeFormView(this, dao, this, AbstractFormView.ACTION_UPDATE, employee);
    }

    @Override
    protected int getIdFromModel(Employee employee) {
        return employee.getId();
    }
}
