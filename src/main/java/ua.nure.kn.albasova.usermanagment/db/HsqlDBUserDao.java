package ua.nure.kn.kukhtin.usermanagment.db;

import ua.nure.kn.kukhtin.usermanagment.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

class HsqlDBUserDao implements UserDAO{
    /**
     * This class implements CRUD methods for concrete database - HSQLDB
     */

    public static final String FIND_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_NAMES_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname = ? AND lastname = ?";
    private ConnectionFactory connectionFactory;

    public HsqlDBUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqlDBUserDao() {
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateOfBirth().getTime()));
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of inserted raws: " + number);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY ()");
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()){
                user.setId(new Long(keys.getLong(1)));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) throws DatabaseException {
    User user;
        try {
            user = null;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1,id);
            ResultSet oneUserResultSet = preparedStatement.executeQuery();
           if (oneUserResultSet.next()){
                user = new User();
                user.setId(new Long(oneUserResultSet.getLong("ID")));
                user.setFirstName(oneUserResultSet.getString("FIRSTNAME"));
                user.setLastName(oneUserResultSet.getString("LASTNAME"));
                user.setDateOfBirth(oneUserResultSet.getDate("DATEOFBIRTH"));
            }
            connection.close();
            preparedStatement.close();
            oneUserResultSet.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<>();

        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<User> find(String firstName, String lastName) throws DatabaseException {
        Collection<User> result = new LinkedList<>();

        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAMES_QUERY);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of updated raws: " + number);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(DELETE_QUERY);
            statement.setLong(1,user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of deleted raws: " + number);
            }


            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
