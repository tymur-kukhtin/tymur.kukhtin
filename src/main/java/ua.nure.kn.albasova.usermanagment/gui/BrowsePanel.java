package ua.nure.kn.kukhtin.usermanagment.gui;

import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.db.DatabaseException;
import ua.nure.kn.kukhtin.usermanagment.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowsePanel extends javax.swing.JPanel implements ActionListener {

    private JScrollPane tablePanel;
    private JTable userTable;
    private JPanel buttonsPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton detailsButton;
    private MainFrame parent;
    private final String MESSAGE = "Are you sure you want delete this user ?";

    public BrowsePanel (MainFrame frame){
        parent = frame;
        initialize();
    }

    private void initialize(){
        this.setName("browsePanel");
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(),BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonsPanel() {
        if(buttonsPanel == null){
            buttonsPanel = new JPanel();
            buttonsPanel.add(getAddButton(), null);
            buttonsPanel.add(getEditButton(), null);
            buttonsPanel.add(getDeleteButton(), null);
            buttonsPanel.add(getDetailsButton(), null);
        }
        return buttonsPanel;
    }

    private JButton getAddButton() {
        if(addButton == null){
            addButton = new JButton();
            addButton.setText(Messages.getString("user_managment.browsePanel1"));//localize
            addButton.setName("addButton");
            addButton.setActionCommand("add");
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JButton getEditButton() {
        if(editButton == null){
            editButton = new JButton();
            editButton.setText(Messages.getString("user_managment.browsePanel2"));//localize
            editButton.setName("editButton");
            editButton.setActionCommand("edit");
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getDeleteButton() {
        if(deleteButton == null){
            deleteButton = new JButton();
            deleteButton.setText(Messages.getString("user_managment.browsePanel3"));//localize
            deleteButton.setName("deleteButton");
            deleteButton.setActionCommand("delete");
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getDetailsButton() {
        if(detailsButton == null){
            detailsButton = new JButton();
            detailsButton.setText(Messages.getString("user_managment.browsePanel4"));//localize
            detailsButton.setName("detailsButton");
            detailsButton.setActionCommand("details");
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JScrollPane getTablePanel() {
        if(tablePanel == null){
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if(userTable == null){
            userTable = new JTable();
            userTable.setName("userTable");
        }

        return userTable;
    }

    public void initTable() {
        UserTableModel model;
        try {
            model = new UserTableModel(parent.getDao().findAll());
        } catch (DatabaseException e) {
            model = new UserTableModel(new ArrayList());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error1111",
                    JOptionPane.ERROR_MESSAGE);
        }
        getUserTable().setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if("add".equalsIgnoreCase(actionCommand)){
            this.setVisible(false);
            parent.showAddPanel();
        }
        if("edit".equalsIgnoreCase(actionCommand)){
            this.setVisible(false);
            parent.showEditPanel();
        }
        if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$

            int result = JOptionPane.showConfirmDialog(parent, MESSAGE, "Delete confirm", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    parent.getDao().delete(getSelectedUser());

                    getUserTable().setModel(new UserTableModel(parent.getDao().findAll()));
                } catch (DatabaseException e1) {
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if("details".equalsIgnoreCase(actionCommand)) {
            this.setVisible(false);
            parent.showDetailsPanel();
        }
    }

    public User getSelectedUser() {
        int selectedRow = getUserTable().getSelectedRow();
        if (selectedRow == -1)
            return null;
        try {
            User user = parent.getDao().find((Long) getUserTable().getValueAt(selectedRow, 0));
            return user;
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
