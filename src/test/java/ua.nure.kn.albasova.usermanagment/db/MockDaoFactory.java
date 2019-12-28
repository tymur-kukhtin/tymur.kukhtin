package ua.nure.kn.kukhtin.usermanagment.db;

import com.mockobjects.dynamic.Mock;
import ua.nure.kn.kukhtin.usermanagment.User;

public class MockDaoFactory extends DaoFactory {

    private Mock mockUserDao;

    public MockDaoFactory() {
        mockUserDao = new Mock(UserDAO.class);
    }

    /**
     *
     * @return Mock object that replaces real object
     */
    public Mock getMockUserDAO() {
        return mockUserDao;
    }

    /**
     * method creates a proxy instance of UserDAO
     * @return proxy instance of UserDAO
     */
    @Override
    public UserDAO getUserDAO() {
        return (UserDAO) mockUserDao.proxy();
    }
}
