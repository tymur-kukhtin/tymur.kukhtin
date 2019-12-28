package ua.nure.kn.kukhtin.usermanagment.gui;

import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.db.DatabaseException;
import ua.nure.kn.kukhtin.usermanagment.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddPanel extends JPanel implements ActionListener {

    private MainFrame parent;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private Color bgColor;

    public AddPanel (MainFrame parent){
        this.parent = parent;
        initialize();
    }

    private void initialize(){
        this.setName("addPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(),BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        if(buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    private JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("user_managment.addPanel5"));
            cancelButton.setName("cancelButton");
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JButton getOkButton() {
        if(okButton == null){
            okButton = new JButton();
            okButton.setText(Messages.getString("user_managment.addPanel4"));
            okButton.setName("okButton");
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JPanel getFieldPanel() {
        if(fieldPanel == null){
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3,2));
            addLabeledField(fieldPanel, Messages.getString("user_managment.addPanel1"), getFirstNameField());
            addLabeledField(fieldPanel, Messages.getString("user_managment.addPanel2"), getLastNameField());
            addLabeledField(fieldPanel, Messages.getString("user_managment.addPanel3"), getDateOfBirthField());
        }
        return fieldPanel;
    }

    private JTextField getDateOfBirthField() {
        if(dateOfBirthField == null){
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField");
        }
        return  dateOfBirthField;
    }

    private JTextField getLastNameField() {
        if(lastNameField == null){
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }
        return lastNameField;
    }

    private void addLabeledField(JPanel panel, String labeltext, JTextField textField) {
        JLabel label = new JLabel(labeltext);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JTextField getFirstNameField() {
        if(firstNameField == null){
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }
        return firstNameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("ok".equalsIgnoreCase(e.getActionCommand())){
            User user = new User();
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            //DateFormat format = DateFormat.getDateInstance();
            DateFormat format = new SimpleDateFormat();
            try {
                user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));

            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            try {
                parent.getDao().create(user);
            } catch (DatabaseException e2) {
                JOptionPane.showMessageDialog(this, e2.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        clearFields();
        this.setVisible(false);
        parent.showBrowsePanel();
    }

    private void clearFields() {
        getFirstNameField().setText("");
        getFirstNameField().setBackground(bgColor);

        getLastNameField().setText("");
        getLastNameField().setBackground(bgColor);

        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(bgColor);
    }
}
