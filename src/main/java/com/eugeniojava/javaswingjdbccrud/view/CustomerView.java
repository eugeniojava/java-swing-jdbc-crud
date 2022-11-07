package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.GenericDao;
import com.eugeniojava.javaswingjdbccrud.model.Customer;
import javax.swing.JFrame;

public class CustomerView extends GenericView<Customer> {
    public CustomerView(JFrame parent, GenericDao<Customer> dao) {
        super(parent, dao);
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
    protected void instantiateFormViewToCreate() {
        new CustomerFormView(this, dao, this, GenericFormView.ACTION_CREATE, null);
    }

    @Override
    protected void instantiateFormViewToUpdate(Customer customer) {
        new CustomerFormView(this, dao, this, GenericFormView.ACTION_UPDATE, customer);
    }

    @Override
    protected int getIdFromModel(Customer customer) {
        return customer.getId();
    }
}
