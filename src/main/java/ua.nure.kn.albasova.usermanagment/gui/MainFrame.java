package ua.nure.kn.kukhtin.usermanagment.gui;

import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.db.DaoFactory;
import ua.nure.kn.kukhtin.usermanagment.db.UserDAO;
import ua.nure.kn.kukhtin.usermanagment.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private UserDAO dao;
    private AddPanel addPanel;
    private EditPanel editPanel;
    private DetailsPanel detailsPanel;

    public UserDAO getDao() {
        return dao;
    }


    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDAO();
        initialize();
    }

    /**
     * In this method we create settings for our window
     */
    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("user_managment.mainFrame1"));
        this.setContentPane(getContentPanel());

    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    private AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        // ((EditPanel) editPanel).resetFields();
        return editPanel;
    }


    public User getSelectedUser() {
        return ((BrowsePanel) browsePanel).getSelectedUser();
    }

    public void showEditPanel() {
        showPanel(getEditPanel());
    }

    private DetailsPanel getDetailsPanel() {
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this);
        }
        return detailsPanel;
    }

    public void showDetailsPanel() {
        showPanel(getDetailsPanel());
    }
}