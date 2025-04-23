package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.LoginTracker;

public class LoginTrackerTests {

    @Test
    public void testFirstLogin() {
        LoginTracker loginTracker = new LoginTracker();
        loginTracker.recordLogin("testuser");
        assertEquals(1, loginTracker.getLoginCount("testuser"));
    }

    @Test
    public void testMultipleLogins() {
        LoginTracker loginTracker = new LoginTracker();
        loginTracker.recordLogin("testuser1");
        loginTracker.recordLogin("testuser1");
        assertEquals(2, loginTracker.getLoginCount("testuser1"));
    }
}
