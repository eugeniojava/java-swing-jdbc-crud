package main.java.com.eugeniojava.view;

import main.java.com.eugeniojava.dao.impl.CustomerDaoImpl;
import main.java.com.eugeniojava.dao.impl.EmployeeDaoImpl;
import main.java.com.eugeniojava.dao.impl.ProductDaoImpl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {
    private static final String TITLE = "Store management system";

    public MainView() {
        setTitle(TITLE);
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        JLabel jLabelTitle = new JLabel(TITLE);
        jLabelTitle.setBounds(120, 10, 200, 30);
        add(jLabelTitle);

        JLabel jLabelCustomers = new JLabel("Customers");
        jLabelCustomers.setBounds(10, 40, 100, 30);
        add(jLabelCustomers);
        JButton jButtonManageCustomers = new JButton("Manage customers");
        jButtonManageCustomers.setBounds(110, 40, 200, 30);
        jButtonManageCustomers.addActionListener(this::jButtonManageCustomers);
        add(jButtonManageCustomers);

        JLabel jLabelEmployees = new JLabel("Employees");
        jLabelEmployees.setBounds(10, 70, 100, 30);
        add(jLabelEmployees);
        JButton jButtonManageEmployees = new JButton("Manage employees");
        jButtonManageEmployees.setBounds(110, 70, 200, 30);
        jButtonManageEmployees.addActionListener(this::jButtonManageEmployees);
        add(jButtonManageEmployees);

        JLabel jLabelProducts = new JLabel("Products");
        jLabelProducts.setBounds(10, 100, 100, 30);
        add(jLabelProducts);
        JButton jButtonManageProducts = new JButton("Manage products");
        jButtonManageProducts.setBounds(110, 100, 200, 30);
        jButtonManageProducts.addActionListener(this::jButtonManageProducts);
        add(jButtonManageProducts);

        JButton jButtonClose = new JButton("Close");
        jButtonClose.setBounds(150, 130, 100, 30);
        jButtonClose.addActionListener(this::jButtonCloseActionPerformed);
        add(jButtonClose);
    }

    private void jButtonManageCustomers(ActionEvent actionEvent) {
        new CustomerView(this, new CustomerDaoImpl());
    }

    private void jButtonManageEmployees(ActionEvent actionEvent) {
        new EmployeeView(this, new EmployeeDaoImpl());
    }

    private void jButtonManageProducts(ActionEvent actionEvent) {
        new ProductView(this, new ProductDaoImpl());
    }

    private void jButtonCloseActionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
