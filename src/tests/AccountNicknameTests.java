package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;

public class AccountNicknameTests {
    @Test
    public void testDefaultNickname() {
        BankAccount account = new BankAccount("testuser", "password", "ACC001");
        // The default nickname should be set to the username
        assertEquals("testuser", account.getNickname());
    }
    
    @Test
    public void testChangeNickname() {
        BankAccount account = new BankAccount("testuser", "password", "ACC001");
        account.setNickname("CoolUser");
        assertEquals("CoolUser", account.getNickname());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyNickname() {
        BankAccount account = new BankAccount("testuser", "password", "ACC001");
        account.setNickname("");
    }
}
