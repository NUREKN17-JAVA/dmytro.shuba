package ua.nure.shuba.usermanagement;

import junit.framework.TestCase;

import java.util.Calendar;

public class UserTest extends TestCase {

    // YEARS
    private static final int CURRENT_YEAR = 2019;
    private static final int YEAR_OF_BIRTH = 1998;

    // Data when birthday in the past
    private static final int DAY_OF_BIRTH_PAST = 8;
    private static final int MONTH_OF_BIRTH_PAST = Calendar.SEPTEMBER;
    private static final int AGE_PAST = CURRENT_YEAR - YEAR_OF_BIRTH;

    // Data when birthday was yesterday
    private static final int DAY_OF_BIRTH_YESTERDAY = 26;
    private static final int MONTH_OF_BIRTH_YESTERDAY = Calendar.OCTOBER;
    private static final int AGE_YESTERDAY = CURRENT_YEAR - YEAR_OF_BIRTH;

    // Data when birthday today
    private static final int DAY_OF_BIRTH_TODAY = 27;
    private static final int MONTH_OF_BIRTH_TODAY = Calendar.OCTOBER;
    private static final int AGE_TODAY = CURRENT_YEAR - YEAR_OF_BIRTH;

    // Data when birthday tomorrow
    private static final int DAY_OF_BIRTH3 = 29;
    private static final int MONTH_OF_BIRTH3 = Calendar.OCTOBER;
    private static final int AGE_TOMORROW = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    // Data when birthday in the future
    private static final int DAY_OF_BIRTH_FUTURE = 13;
    private static final int MONTH_OF_BIRTH_FUTURE = Calendar.DECEMBER;
    private static final int AGE_FUTURE = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    private User user;

    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetAgeIfBirthdayToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_TODAY, DAY_OF_BIRTH_TODAY);
        user.setBirthdayDate(calendar.getTime());
        assertEquals(AGE_TODAY, user.getAge());
    }

    public void testGetFullName() {
        user.setFirstName("Dmytro");
        user.setLastName("Shuba");
        assertEquals("Shuba, Dmytro", user.getFullName());
    }

    public void testGetAgeIfBirthdayInTheFuture() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_FUTURE, DAY_OF_BIRTH_FUTURE);
        user.setBirthdayDate(calendar.getTime());
        assertEquals(AGE_FUTURE, user.getAge());
    }

    public void testGetAgeIfBirthdayYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_YESTERDAY,
                DAY_OF_BIRTH_YESTERDAY);
        user.setBirthdayDate(calendar.getTime());
        assertEquals(AGE_YESTERDAY, user.getAge());
    }

    public void testGetAgeIfBirthdayTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH3, DAY_OF_BIRTH3);
        user.setBirthdayDate(calendar.getTime());
        assertEquals(AGE_TOMORROW, user.getAge());
    }

    public void testGetAgeIfBirthdayInThePast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_PAST, DAY_OF_BIRTH_PAST);
        user.setBirthdayDate(calendar.getTime());
        assertEquals(AGE_PAST, user.getAge());
    }
}
