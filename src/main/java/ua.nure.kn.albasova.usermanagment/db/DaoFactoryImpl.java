package ua.nure.kn.kukhtin.usermanagment.db;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public UserDAO getUserDAO(){
        UserDAO result = null;
        try {
            Class clazz = Class.forName(properties.getProperty(USER_DAO));
            result = (UserDAO) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}
