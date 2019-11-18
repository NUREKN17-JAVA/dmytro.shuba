package ua.nure.shuba.usermanagement.db;

import ua.nure.shuba.usermanagement.entity.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class HsqldbUserDAO implements DAO<User> {

    private ConnectionFactory connectionFactory;
    private static final String INSERT_QUERY = "INSERT INTO USERS (FIRSTNAME, LASTNAME, DATEOFBIRTH) VALUES (?, ?, ?)";
    private static final String CALL_IDENTITY = "call IDENTITY()";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM USERS";
    private static final String UPDATE_QUERY = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, DATEOFBIRTH = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM USERS WHERE ID= ?";
    private static final String FIND_QUERY = "SELECT * FROM USERS WHERE ID = ?";

    public HsqldbUserDAO() {
    }

    public HsqldbUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3,
                    new Date(user.getDateOfBirth().getTime()));
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseException(
                        "Number of the inserted rows: " + n);
            }
            CallableStatement callableStatement = connection
                    .prepareCall(CALL_IDENTITY);
            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
            return user;
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3,
                    new Date(user.getDateOfBirth().getTime()));
            preparedStatement.setLong(4, user.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows != 1) {
                throw new DatabaseException(
                        "Number of inserted rows: " + changedRows);
            }

            connection.close();
            preparedStatement.close();
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
            statement.setLong(1, user.getId());
            int removedRows = statement.executeUpdate();
            if (removedRows != 1) {
                throw new DatabaseException(
                        "Number of removed rows: " + removedRows);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        User user = new User();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(FIND_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return user;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Collection<User> users = new LinkedList<>();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                users.add(user);
            }
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return users;
    }
}
