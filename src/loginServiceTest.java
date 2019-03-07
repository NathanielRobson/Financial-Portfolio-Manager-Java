import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class loginServiceTest {
    //this method test if the user exits in the file
    @Test
    public void usernameTest() throws Exception {
        loginService login = new loginService();
        assertEquals(true, login.getUserNames().contains("KL"));
        assertEquals(false, login.getUserNames().contains("Koranteng"));
    }

    @Test
    //here it test if the user password exits in the file
    public void passwordTest() throws Exception {
        loginService login = new loginService();
        assertEquals(true, login.getPassWords().contains("teamKL"));
        assertEquals(false, login.getPassWords().contains("Koranteng"));
    }
}