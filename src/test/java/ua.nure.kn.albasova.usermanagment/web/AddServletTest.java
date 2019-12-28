package ua.nure.kn.kukhtin.usermanagment.web;

import ua.nure.kn.kukhtin.usermanagment.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd(){
        Date date = new Date();
        User newUser = new User("John", "Doe", date);
        User user = new User(new Long(1000), "John", "Doe", date);
        getMockUserDao().expectAndReturn("create",newUser, user);

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        //addRequestParameter("date", DateFormat.getInstance().format(date));
        addRequestParameter("date", format.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();

    }

    public void testAddEmptyFirstName(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", format.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        addRequestParameter("firstName", "John");
        addRequestParameter("date", format.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate(){
        Date date = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDateIncorrect(){
        Date date = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", "dateinwords");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}
