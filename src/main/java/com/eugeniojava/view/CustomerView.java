package main.java.com.eugeniojava.view;

import javax.swing.JFrame;
import main.java.com.eugeniojava.dao.GenericDao;
import main.java.com.eugeniojava.model.Customer;

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
