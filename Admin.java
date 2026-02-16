import java.io.Serializable;

public class Admin implements Serializable {
    private final String username = "admin";
    private final String password = "1234";

    public boolean login(String u, String p) {
        return u.equals(username) && p.equals(password);
    }
}
