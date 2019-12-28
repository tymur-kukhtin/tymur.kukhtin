package ua.nure.kn.kukhtin.usermanagment.gui;

import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.kukhtin.usermanagment.User;
import ua.nure.kn.kukhtin.usermanagment.db.DaoFactory;
import ua.nure.kn.kukhtin.usermanagment.db.MockDaoFactory;
import ua.nure.kn.kukhtin.usermanagment.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainFrameTest extends JFCTestCase {
    private Mock mockUserDao;
    //private List<User> users;
    private MainFrame mainFrame;

    /**
     * In the setUp () method, we replace the real database with
     * a Mock object to test the graphical interface without modifying the database.
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();

        Properties properties = new Properties();
        properties.setProperty("dao.factory",MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDAO();
        //User expectedUser = new User(1000L,"George", "Bush", new Date());
        //users = Collections.singletonList(expectedUser);
        mockUserDao.expectAndReturn("findAll",new ArrayList());
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    /**
     * This method try to find gui component by it's type and name,
     * If we can't find component, method return message about this problem
     * @param componentClass - Class of gui component
     * @param name - name of gui that we set during initialization
     * @return component, that we found
     */
    private Component find(Class<?> componentClass, String name){
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Couldn`t find component'" + name + "'", component);
        return component;

    }


    public void tearDown() throws Exception {
        mockUserDao.verify();
        mainFrame.setVisible(false);
        getHelper().cleanUp(this);
        super.tearDown();
    }

    /**
     * This test checks main panel of our gui
     */
    public void testBrowseControls() {
        find(JPanel.class, "browsePanel");
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        assertEquals(Messages.getString("user_managment.userTable"), table.getColumnName(0));
        assertEquals(Messages.getString("user_managment.userTable2"), table.getColumnName(1));
        assertEquals(Messages.getString("user_managment.userTable3"), table.getColumnName(2));
        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }

    /**
     * Test adding new user to database, using graphical interface
     * This test simulates the actions of a person who
     * is trying to add a new record to the database using a graphical interface.
     */
    public void testAddUser(){
        String firstName = "John";
        String lastName = "Doe";
        Date now = new Date();

        DateFormat formatter = new SimpleDateFormat();
        String date = formatter.format(now);

        User user = new User(firstName, lastName, now);
        User expectedUser = new User(1L, firstName,lastName, now);

        mockUserDao.expectAndReturn("create", user, expectedUser);

        ArrayList users = new ArrayList();
        users.add(expectedUser);
        mockUserDao.expectAndReturn("findAll", users);

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(0,table.getRowCount());

        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(1,table.getRowCount());
    }
}
