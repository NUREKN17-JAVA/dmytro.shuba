package ua.nure.shuba.usermanagement.db;

import com.mockobjects.dynamic.Mock;
import ua.nure.shuba.usermanagement.entity.User;

public class MockDAOFactory extends DAOFactory {
    private Mock mockUserDao;

    public MockDAOFactory() {
        mockUserDao = new Mock(DAO.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    @SuppressWarnings("unchecked")
    public DAO<User> getUserDao() {
        return (DAO<User>) mockUserDao.proxy();
    }

}
