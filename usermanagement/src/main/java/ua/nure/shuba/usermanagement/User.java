package ua.nure.shuba.usermanagement;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User {

    private static final long SERIAL_VERSION_UID = -5553481350681901428L;

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdayDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects
                .equals(firstName, user.firstName) && Objects
                .equals(lastName, user.lastName) && Objects
                .equals(birthdayDate, user.birthdayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthdayDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    public int getAge() {
        int age = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(getBirthdayDate());
        int birthYear = calendar.get(Calendar.YEAR);
        int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
        int birthMonth = calendar.get(Calendar.MONTH);

        int age1 = currentYear - birthYear;
        int age2 = currentYear - birthYear - 1;

        if (birthMonth < currentMonth) {
            age = age1;
        } else if (birthMonth > currentMonth) {
            age = age2;
        } else if (birthDay > currentDay) {
            age = age2;
        } else if (birthDay < currentDay) {
            age = age1;
        } else {
            age = age1;
        }
        return age;
    }
}
