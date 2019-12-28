package ua.nure.kn.kukhtin.usermanagment.db;

import ua.nure.kn.kukhtin.usermanagment.User;

import java.util.Collection;
import java.util.List;

public interface UserDAO {
    /**
     * Add user to DB table USERS
     * @param user with null id field
     * @return user modified record exemplar with DB auto-generated id field
     */
    public User create(final User user) throws DatabaseException;

    /**
     * Select user from DB table USERS with id =
     * @param id - id of user we want to find
     * @return user record that have such id
     */
    public User find(final Long id) throws DatabaseException;

    /**
     * Select all users from DB table USERS
     * @return list of users records from DB table USERS
     */
    public Collection<User> findAll() throws DatabaseException;

    public Collection<User> find(String firstName, String lastName) throws DatabaseException;
    /**
     * Update user record in DB table USERS if its id equals to id of
     * @param user
     */
    public void update(final User user) throws DatabaseException;

    /**
     * Delete user record in DB table USERS if its id equals to id of
     * @param user
     */
    public void delete(final User user) throws DatabaseException;

    /**
     *
     * @param connectionFactory
     */
    void setConnectionFactory(ConnectionFactory connectionFactory);
}
