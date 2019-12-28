package ua.nure.kn.kukhtin.usermanagment.db;

import java.sql.Connection;

public interface ConnectionFactory {
    /**
     *
     * @return Connection to database
     * @throws DatabaseException
     */
    Connection createConnection() throws DatabaseException;
}
