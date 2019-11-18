package ua.nure.shuba.usermanagement.db;

import ua.nure.shuba.usermanagement.entity.User;

import java.util.Calendar;

public class TestMain {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Dmitry");
        user.setLastName("Shuba");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.NOVEMBER, 20);
        user.setDateOfBirth(calendar.getTime());
        DAO<User> systemUserDAO = DAOFactory.getInstance().getUserDao();
        try {
            User user1 = systemUserDAO.create(user);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
