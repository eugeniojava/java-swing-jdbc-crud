package com.eugeniojava.javaswingjdbccrud.view;

import com.eugeniojava.javaswingjdbccrud.dao.AbstractDao;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public abstract class AbstractFormView<T> extends JFrame {
    public static final String ACTION_CREATE = "Create";
    public static final String ACTION_UPDATE = "Update";
    protected final String action;
    protected final String viewTitle;
    protected final AbstractDao<T> dao;
    protected final AbstractView<T> view;
    protected T t;
    protected JTextField jTextFieldOne;
    protected JTextField jTextFieldTwo;
    protected JTextField jTextFieldThree;

    protected AbstractFormView(JFrame parent, AbstractDao<T> dao, AbstractView<T> view, String action, T t) {
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(parent);
        setResizable(false);
        viewTitle = getModelNameCapitalized() + " form";
        setTitle(viewTitle);
        this.dao = dao;
        this.view = view;
        this.action = action;
        this.t = t;
        initComponents();
        setVisible(true);
    }

    protected abstract String getModelNameCapitalized();

    protected abstract String getModelNameUncapitalized();

    protected abstract String getFieldNameOneCapitalized();

    protected abstract String getFieldNameTwoCapitalized();

    protected abstract String getFieldNameThreeCapitalized();

    protected void initComponents() {
        JLabel jLabelTitle = new JLabel(action + " form");
        jLabelTitle.setBounds(120, 10, 200, 30);
        add(jLabelTitle);
        JLabel jLabelOne = new JLabel(getFieldNameOneCapitalized() + ":");
        jLabelOne.setBounds(10, 40, 100, 30);
        add(jLabelOne);
        jTextFieldOne = new JTextField();
        jTextFieldOne.setBounds(110, 40, 200, 30);
        add(jTextFieldOne);
        JLabel jLabelTwo = new JLabel(getFieldNameTwoCapitalized() + ":");
        jLabelTwo.setBounds(10, 70, 100, 30);
        add(jLabelTwo);
        jTextFieldTwo = new JTextField();
        jTextFieldTwo.setBounds(110, 70, 200, 30);
        add(jTextFieldTwo);
        JLabel jLabelThree = new JLabel(getFieldNameThreeCapitalized() + ":");
        jLabelThree.setBounds(10, 100, 100, 30);
        add(jLabelThree);
        jTextFieldThree = new JTextField();
        jTextFieldThree.setBounds(110, 100, 200, 30);
        add(jTextFieldThree);
        JButton jButtonSave = new JButton("Save");
        jButtonSave.setBounds(40, 130, 100, 30);
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);
        add(jButtonSave);
        JButton jButtonCancel = new JButton("Cancel");
        jButtonCancel.setBounds(160, 130, 100, 30);
        jButtonCancel.addActionListener(this::jButtonCancelActionPerformed);
        add(jButtonCancel);
        if (action.equals(ACTION_UPDATE)) {
            setJTextFieldsFromModel();
        }
    }

    protected abstract void setJTextFieldsFromModel();

    protected abstract void setModelFromJTextFields();

    protected void jButtonSaveActionPerformed(ActionEvent actionEvent) {
        if (validateFields()) {
            if (action.equals(ACTION_CREATE)) {
                T newModel = instantiateModelFromJTextFields();
                if (dao.create(newModel) == 1) {
                    showMessageDialog(
                            this,
                            getModelNameCapitalized() + " successfully created.",
                            AbstractView.DIALOG_TITLE_SUCCESS,
                            INFORMATION_MESSAGE);
                    dispose();
                    view.loadFromDatabase();
                }
            } else if (action.equals(ACTION_UPDATE)) {
                setModelFromJTextFields();
                if (dao.update(t) == 1) {
                    showMessageDialog(
                            this,
                            getModelNameCapitalized() + " successfully updated.",
                            AbstractView.DIALOG_TITLE_SUCCESS,
                            INFORMATION_MESSAGE);
                    dispose();
                    view.loadFromDatabase();
                }
            } else {
                showMessageDialog(
                        this,
                        "Error on creating or updating " + getModelNameUncapitalized() + ".",
                        AbstractView.DIALOG_TITLE_ERROR,
                        ERROR_MESSAGE);
                dispose();
            }
        }
    }

    protected abstract T instantiateModelFromJTextFields();

    protected void jButtonCancelActionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    protected abstract boolean validateFields();

    protected boolean validateStringField(JTextField jTextField, String fieldName) {
        if (jTextField == null || jTextField.getText().isEmpty() || jTextField.getText().trim().isEmpty()) {
            showErrorMessageDialog(fieldName);
            return false;
        }
        return true;
    }

    protected boolean validateIntegerField(JTextField jTextField, String fieldName) {
        try {
            Integer.parseInt(jTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            showErrorMessageDialog(fieldName);
            return false;
        }
    }

    protected boolean validateDoubleField(JTextField jTextField, String fieldName) {
        try {
            Double.parseDouble(jTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            showErrorMessageDialog(fieldName);
            return false;
        }
    }

    protected void showErrorMessageDialog(String fieldName) {
        showMessageDialog(
                this,
                "Field " + fieldName + " is invalid.",
                AbstractView.DIALOG_TITLE_ERROR,
                ERROR_MESSAGE);
    }
}
