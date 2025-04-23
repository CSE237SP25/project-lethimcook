package bankapp;

import java.util.HashMap;
import java.util.Map;

public class LoginTracker {

    private Map<String, Integer> loginCounts;

    public LoginTracker() {
        loginCounts = new HashMap<>();
    }

    public void recordLogin(String username) {
        if (loginCounts.containsKey(username)) {
            int currentCount = loginCounts.get(username);
            loginCounts.put(username, currentCount + 1);
        } else {
            loginCounts.put(username, 1);
        }
    }

    public int getLoginCount(String username) {
        return loginCounts.getOrDefault(username, 0);
    }
}
