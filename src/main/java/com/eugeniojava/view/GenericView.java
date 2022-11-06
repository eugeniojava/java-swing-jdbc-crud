package main.java.com.eugeniojava.view;

import main.java.com.eugeniojava.dao.GenericDao;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public abstract class GenericView<T> extends JFrame {
    public static final String DIALOG_TITLE_SUCCESS = "Success";
    public static final String DIALOG_TITLE_ERROR = "Error";
    protected final String dialogMessageErrorUnselected;
    protected final String viewTitle;
    protected final GenericDao<T> dao;
    protected final DefaultListModel<T> modelDefaultListModel;
    protected List<T> modelList;
    protected JList<T> jListModel;
    protected JTextField jTextFieldName;

    protected GenericView(JFrame parent, GenericDao<T> dao) {
        setSize(320, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(parent);
        setResizable(false);

        dialogMessageErrorUnselected = "Please select a(n) " + getModelNameUncapitalized() + " from the list before " +
                "doing this.";
        viewTitle = getModelNameCapitalized() + " management";
        setTitle(viewTitle);
        this.dao = dao;
        modelDefaultListModel = new DefaultListModel<>();
        modelList = new ArrayList<>();
        initComponents();

        loadFromDatabase();

        setVisible(true);
    }

    protected abstract String getModelNameCapitalized();

    protected abstract String getModelNameUncapitalized();

    public void loadFromDatabase() {
        modelDefaultListModel.clear();
        modelList = dao.findAll();
        modelList.forEach(modelDefaultListModel::addElement);
    }

    protected void initComponents() {
        JLabel jLabelTitle = new JLabel(viewTitle);
        jLabelTitle.setBounds(80, 10, 200, 30);
        add(jLabelTitle);

        JLabel jLabelFilterByName = new JLabel("Filter by name: ");
        jLabelFilterByName.setBounds(10, 40, 100, 30);
        jTextFieldName = new JTextField();
        jTextFieldName.setBounds(100, 40, 120, 30);
        JButton jButtonFilter = new JButton("Filter");
        jButtonFilter.addActionListener(this::jButtonFilterActionPerformed);
        jButtonFilter.setBounds(230, 40, 80, 30);
        add(jLabelFilterByName);
        add(jTextFieldName);
        add(jButtonFilter);

        JButton jButtonCreateModel = new JButton("Create " + getModelNameUncapitalized());
        jButtonCreateModel.setBounds(80, 70, 150, 30);
        jButtonCreateModel.addActionListener(this::jButtonCreateModelActionPerformed);
        add(jButtonCreateModel);

        JButton jButtonUpdateModel = new JButton("Update " + getModelNameUncapitalized());
        jButtonUpdateModel.setBounds(80, 100, 150, 30);
        jButtonUpdateModel.addActionListener(this::jButtonUpdateModelActionPerformed);
        add(jButtonUpdateModel);

        JButton jButtonDeleteModel = new JButton("Delete " + getModelNameUncapitalized());
        jButtonDeleteModel.setBounds(80, 130, 150, 30);
        jButtonDeleteModel.addActionListener(this::jButtonDeleteModelActionPerformed);
        add(jButtonDeleteModel);

        jListModel = new JList<>(modelDefaultListModel);
        jListModel.setBounds(10, 180, 300, 300);
        add(jListModel);

        JButton jButtonClearFilter = new JButton("Clear filter");
        jButtonClearFilter.setBounds(100, 500, 120, 30);
        jButtonClearFilter.addActionListener(this::jButtonClearFilterActionPerformed);
        add(jButtonClearFilter);

        JButton jButtonSaveChanges = new JButton("Save changes");
        jButtonSaveChanges.setBounds(90, 540, 140, 30);
        jButtonSaveChanges.addActionListener(this::jButtonSaveChangesActionPerformed);
        add(jButtonSaveChanges);

        JButton jButtonDiscardChanges = new JButton("Discard changes");
        jButtonDiscardChanges.setBounds(90, 580, 140, 30);
        jButtonDiscardChanges.addActionListener(this::jButtonDiscardChangesActionPerformed);
        add(jButtonDiscardChanges);

        JButton jButtonClose = new JButton("Close");
        jButtonClose.setBounds(110, 620, 100, 30);
        jButtonClose.addActionListener(this::jButtonCloseActionPerformed);
        add(jButtonClose);
    }

    protected void jButtonFilterActionPerformed(ActionEvent actionEvent) {
        String name = jTextFieldName.getText();

        if (!name.isEmpty()) {
            List<T> list = dao.findByName(name);

            if (!list.isEmpty()) {
                modelDefaultListModel.clear();
                modelList = list;
                modelList.forEach(modelDefaultListModel::addElement);
            } else {
                showMessageDialog(
                        this,
                        "There is no " + getModelNameUncapitalized() + " with this name.",
                        DIALOG_TITLE_ERROR,
                        ERROR_MESSAGE);
            }
        } else {
            showMessageDialog(
                    this,
                    "Field name must be filled.",
                    DIALOG_TITLE_ERROR,
                    ERROR_MESSAGE);
        }
    }

    protected void jButtonCreateModelActionPerformed(ActionEvent actionEvent) {
        instantiateFormViewToCreate();
    }

    protected abstract void instantiateFormViewToCreate();

    protected void jButtonUpdateModelActionPerformed(ActionEvent actionActionEvent) {
        T selectedModel = jListModel.getSelectedValue();

        if (selectedModel != null) {
            instantiateFormViewToUpdate(selectedModel);
        } else {
            showMessageDialog(
                    this,
                    dialogMessageErrorUnselected,
                    DIALOG_TITLE_ERROR,
                    ERROR_MESSAGE);
        }
    }

    protected abstract void instantiateFormViewToUpdate(T t);

    protected void jButtonDeleteModelActionPerformed(ActionEvent actionActionEvent) {
        T selectedModel = jListModel.getSelectedValue();

        if (selectedModel != null) {
            if (dao.delete(getIdFromModel(selectedModel)) == 1) {
                showMessageDialog(
                        this,
                        getModelNameCapitalized() + " successfully deleted.",
                        DIALOG_TITLE_SUCCESS,
                        INFORMATION_MESSAGE);
                loadFromDatabase();
            } else {
                showMessageDialog(
                        this,
                        "Error on deleting " + getModelNameUncapitalized() + ".",
                        DIALOG_TITLE_ERROR,
                        ERROR_MESSAGE);
            }
        } else {
            showMessageDialog(
                    this,
                    dialogMessageErrorUnselected,
                    DIALOG_TITLE_ERROR,
                    ERROR_MESSAGE);
        }
    }

    protected abstract int getIdFromModel(T t);

    protected void jButtonClearFilterActionPerformed(ActionEvent actionEvent) {
        jTextFieldName.setText(null);
        loadFromDatabase();
    }

    protected void jButtonSaveChangesActionPerformed(ActionEvent actionEvent) {
        if (dao.commitChangesIfTrueElseRollback(true)) {
            showMessageDialog(
                    this,
                    "Changes successfully saved.",
                    DIALOG_TITLE_SUCCESS,
                    INFORMATION_MESSAGE);
        } else {
            showMessageDialog(
                    this,
                    "Error on saving changes.",
                    DIALOG_TITLE_ERROR,
                    ERROR_MESSAGE);
        }
    }

    protected void jButtonDiscardChangesActionPerformed(ActionEvent actionEvent) {
        if (dao.commitChangesIfTrueElseRollback(false)) {
            showMessageDialog(
                    this,
                    "Changes successfully discarded.",
                    DIALOG_TITLE_SUCCESS,
                    INFORMATION_MESSAGE);
        } else {
            showMessageDialog(
                    this,
                    "Error on discarding changes.",
                    DIALOG_TITLE_ERROR,
                    ERROR_MESSAGE);
        }
        loadFromDatabase();
    }

    protected void jButtonCloseActionPerformed(ActionEvent actionEvent) {
        dispose();
    }
}
