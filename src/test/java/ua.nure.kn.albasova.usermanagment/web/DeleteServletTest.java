package ua.nure.kn.kukhtin.usermanagment.web;

import ua.nure.kn.kukhtin.usermanagment.User;

import java.util.Date;

public class DeleteServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DeleteServlet.class);
    }

    public void testDelete() {
        Date date = new Date();
        User user = new User(new Long(1000), "John", "Doe", date);
        getWebMockObjectFactory().getMockSession().setAttribute("user", user);
        addRequestParameter("okButton","Ok");
        getMockUserDao().expect("delete", user);
        doPost();
    }

    public void testCancelDelete() {
        Date date = new Date();
        User user = new User(new Long(1000), "John", "Doe", date);
        getWebMockObjectFactory().getMockSession().setAttribute("user", user);
        addRequestParameter("cancelButton", "Cancel");
        doPost();
    }
}
