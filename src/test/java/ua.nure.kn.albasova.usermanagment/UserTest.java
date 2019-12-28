package ua.nure.kn.kukhtin.usermanagment;

import org.junit.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {
    private static final long ID = 1L;
    private static final String NAME = "Ivan";
    private static final String SURNAME = "Ivanov";
    private User user;
    //
    //THIS TESTS WERE RELEVANT ON 24.10.2018
    //
    //
    private static final int CURRENT_YEAR = 2018;
    private static final int YEAR_OF_BIRTH = 1999;

    // test 1
    // day and month isn't actual
    private static final int ETALONE_AGE_1 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_1 = 3;
    private static final int MONTH_OF_BIRTH_1 = Calendar.JULY;

    // test 2
    // day isn't actual, but month is
    private static final int ETALONE_AGE_2 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_2 = 3;
    private static final int MONTH_OF_BIRTH_2 = Calendar.NOVEMBER;

    // test 3
    // month isn't actual, but day is
    private static final int ETALONE_AGE_3 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_3 = 27;
    private static final int MONTH_OF_BIRTH_3 = Calendar.AUGUST;

    // test 4
    // day and month are actual
    private static final int ETALONE_AGE_4 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_4 = 29;
    private static final int MONTH_OF_BIRTH_4 = Calendar.DECEMBER;

    // test 5
    // day and month are current
    private static final int ETALONE_AGE_5 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_5 = 24;
    private static final int MONTH_OF_BIRTH_5 = Calendar.OCTOBER;

    //test 6
    // day is current, month isn't actual
    private static final int ETALONE_AGE_6 = CURRENT_YEAR - YEAR_OF_BIRTH ;
    private static final int DAY_OF_BIRTH_6 = 24;
    private static final int MONTH_OF_BIRTH_6 = Calendar.SEPTEMBER;

    // test 7
    // day is current, month is actual
    private static final int ETALONE_AGE_7 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_7 = 24;
    private static final int MONTH_OF_BIRTH_7 = Calendar.DECEMBER;

    // test 8
    // month is current, day isn't actual
    private static final int ETALONE_AGE_8 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_8 = 7;
    private static final int MONTH_OF_BIRTH_8 = Calendar.OCTOBER;

    // test 9
    // month is current, day is actual
    private static final int ETALONE_AGE_9 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_9 = 27;
    private static final int MONTH_OF_BIRTH_9 = Calendar.OCTOBER;

    @Before
    public void setUp() throws Exception {
        user = new User(ID, NAME, SURNAME, new Date());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetFullname() {
        assertEquals("Ivan Ivanov", user.getFullName());
    }

    @Test
    public void testGetAge1(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_1,DAY_OF_BIRTH_1);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_1, user.getAge());
    }

    @Test
    public void testGetAge2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_2,DAY_OF_BIRTH_2);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_2, user.getAge());
    }

    @Test
    public void testGetAge3(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_3,DAY_OF_BIRTH_3);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_3, user.getAge());
    }

    @Test
    public void testGetAge4(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_4,DAY_OF_BIRTH_4);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_4, user.getAge());
    }

    @Test
    public void testGetAge5(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_5,DAY_OF_BIRTH_5);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_5, user.getAge());
    }

    @Test
    public void testGetAge6(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_6,DAY_OF_BIRTH_6);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_6, user.getAge());
    }

    @Test
    public void testGetAge7(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_7,DAY_OF_BIRTH_7);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_7, user.getAge());
    }

    @Test
    public void testGetAge8(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_8,DAY_OF_BIRTH_8);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_8, user.getAge());
    }

    @Test
    public void testGetAge9(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_9,DAY_OF_BIRTH_9);
        Date dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALONE_AGE_9, user.getAge());
    }

}