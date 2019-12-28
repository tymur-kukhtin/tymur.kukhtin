package ua.nure.kn.kukhtin.usermanagment.web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }
    public void testDetails(){
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        addRequestParameter("date", format.format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
}
