package ua.nure.shuba.usermanagement.db;

import ua.nure.shuba.usermanagement.entity.User;

public class DAOFactoryImpl extends DAOFactory {

    @SuppressWarnings("unchecked")
    public DAO<User> getUserDao() {
        DAO<User> result;
        try {
            Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
            result = (DAO<User>) clazz.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}