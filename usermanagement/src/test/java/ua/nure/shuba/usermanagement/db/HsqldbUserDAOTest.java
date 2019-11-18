package java.ua.nure.shuba.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import ua.nure.shuba.usermanagement.db.ConnectionFactory;
import ua.nure.shuba.usermanagement.db.ConnectionFactoryImpl;
import ua.nure.shuba.usermanagement.db.DatabaseException;
import ua.nure.shuba.usermanagement.db.HsqldbUserDAO;
import ua.nure.shuba.usermanagement.entity.User;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class HsqldbUserDAOTest extends DatabaseTestCase {

    private HsqldbUserDAO dao;
    private static final int TEST_CREATE_DAY = 9;
    private static final int TEST_CREATE_MONTH = Calendar.OCTOBER;
    private static final int TEST_CREATE_YEAR = 1999;
    private static final String TEST_CREATE_FIRSTNAME = "Dmitry";
    private static final String TEST_CREATE_LASTNAME = "Shuba";

    private static final String DELETE_TEST_LASTNAME = "Ivan";
    private static final String DELETE_TEST_NAME = "Dorn";

    private static final String UPDATE_NAME = "Misha";
    private static final String UPDATE_LNAME = "Nikolaev";
    private static final Date UPDATE_DATE = new Date(1999 - 20 - 12);

    private static final long TEST_ID = 5L;
    private ConnectionFactory connectionFactory;

    protected void setUp() throws Exception {
        super.setUp();
        dao = new HsqldbUserDAO(connectionFactory);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreate() throws DatabaseException {
        User user = new User();
        user.setFirstName(TEST_CREATE_FIRSTNAME);
        user.setLastName(TEST_CREATE_LASTNAME);
        Calendar calendar = Calendar.getInstance();
        calendar.set(TEST_CREATE_YEAR, TEST_CREATE_MONTH, TEST_CREATE_DAY);
        user.setDateOfBirth(calendar.getTime());
        assertNull(user.getId());
        User userToCheck = dao.create(user);
        assertNotNull(userToCheck);
        assertNotNull(userToCheck.getId());
        assertEquals(user.getFirstName(),
                userToCheck.getFirstName());
        assertEquals(user.getLastName(), userToCheck.getLastName());
        assertEquals(user.getDateOfBirth(),
                userToCheck.getDateOfBirth());
    }

    public void testFindAll() throws DatabaseException {
        Collection<User> users = dao.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    public void testUpdate() throws DatabaseException {
        User user = new User();
        user.setFirstName(DELETE_TEST_NAME);
        user.setLastName(DELETE_TEST_LASTNAME);
        user.setDateOfBirth(new Date());

        User userToCheck = dao.create(user);
        assertEquals(DELETE_TEST_NAME, userToCheck.getFirstName());
        assertEquals(DELETE_TEST_LASTNAME, userToCheck.getLastName());
        assertEquals(user.getDateOfBirth(), userToCheck.getDateOfBirth());

        userToCheck.setFirstName(UPDATE_NAME);
        userToCheck.setLastName(UPDATE_LNAME);
        userToCheck.setDateOfBirth(UPDATE_DATE);

        dao.update(userToCheck);

        assertEquals(UPDATE_NAME, userToCheck.getFirstName());
        assertEquals(UPDATE_LNAME, userToCheck.getLastName());
        assertEquals(UPDATE_DATE, userToCheck.getDateOfBirth());
    }

    public void testDelete() throws DatabaseException {
        User user = new User();
        user.setFirstName(DELETE_TEST_NAME);
        user.setLastName(DELETE_TEST_LASTNAME);
        user.setDateOfBirth(new Date());
        User userToCheck = dao.create(user);
        assertNotNull("User does not exist", userToCheck);
        long testId = userToCheck.getId();
        dao.delete(userToCheck);
        assertNull(dao.find(testId));
    }

    public void testFind() throws DatabaseException {
        User user = dao.find(TEST_ID);
        assertNotNull("User does not exist", user);
        assertEquals("FristName is not equal", "TestFind", user.getFirstName());
        assertEquals("Last Name is not equal", "User", user.getLastName());
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl("sa", "",
                "jdbc:hsqldb:file:db/usermanagement", "org.hsqldb.jdbcDriver");
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(
                new File("./src/test/java/resources/usersDataSet.xml"));
    }
}