package ua.nure.shuba.usermanagement;

import junit.framework.TestCase;
import ua.nure.shuba.usermanagement.db.DAO;
import ua.nure.shuba.usermanagement.db.DAOFactory;
import ua.nure.shuba.usermanagement.entity.User;

public class DAOFactoryTest extends TestCase {

    public void testGetUserDAO() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertNotNull("DAOFactory instance is null", daoFactory);
        DAO<User> result = daoFactory.getUserDao();
        assertNotNull("UserDao instance is null", result);
    }
}
