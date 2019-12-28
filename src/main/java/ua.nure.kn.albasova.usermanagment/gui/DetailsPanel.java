package ua.nure.kn.kukhtin.usermanagment.gui;

import ua.nure.kn.kukhtin.usermanagment.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

public class DetailsPanel extends JPanel implements ActionListener {

    private MainFrame parentFrame;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JButton okButton;
    private JLabel dateOfBirthLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private final User user;
    private final String dateOfBirth;
    private JLabel idLabel;

    public DetailsPanel(MainFrame mainFrame) {
        parentFrame = mainFrame;
        user = parentFrame.getSelectedUser();
        dateOfBirth = DateFormat.getDateInstance().format(user.getDateOfBirth());
        initialize();
    }

    private void initialize() {
        this.setName("detailsPanel"); //$NON-NLS-1$
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);

    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton());
        }
        return buttonPanel;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText("OK"); //$NON-NLS-1$
            okButton.setName("okButton"); //$NON-NLS-1$
            okButton.setActionCommand("ok"); //$NON-NLS-1$
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(4, 1));
            fieldPanel.add(getIdLabel());
            fieldPanel.add(getFirstNameLabel());
            fieldPanel.add(getLastNameLabel());
            fieldPanel.add(getDateOfBirthLabel());
        }
        return fieldPanel;
    }

    private JLabel getDateOfBirthLabel() {
        if (dateOfBirthLabel == null) {
            dateOfBirthLabel = new JLabel();
            dateOfBirthLabel.setName("dateOfBirthLabel"); //$NON-NLS-1$
            dateOfBirthLabel.setText(dateOfBirth);
        }
        return dateOfBirthLabel;
    }

    private JLabel getLastNameLabel() {
        if (lastNameLabel == null) {
            lastNameLabel = new JLabel();
            lastNameLabel.setName("lastNameLabel"); //$NON-NLS-1$
            lastNameLabel.setText(user.getLastName());
        }
        return lastNameLabel;
    }

    private JLabel getFirstNameLabel() {
        if (firstNameLabel == null) {
            firstNameLabel = new JLabel();
            firstNameLabel.setName("firstNameLabel"); //$NON-NLS-1$
            firstNameLabel.setText(user.getFirstName());
        }
        return firstNameLabel;
    }
    private JLabel getIdLabel() {
        if (idLabel == null) {
            idLabel = new JLabel();
            idLabel.setName("idLabel"); //$NON-NLS-1$
            idLabel.setText(String.valueOf(user.getId()));
        }
        return idLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ok".equalsIgnoreCase(e.getActionCommand())) {//$NON-NLS-1$
            this.setVisible(false);
        }
        this.setVisible(false);
        parentFrame.showBrowsePanel();
    }

}
