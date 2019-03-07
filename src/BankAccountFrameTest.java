import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountFrameTest {

    @Test
    public void main() {
        BankAccountFrame bankaccount = new BankAccountFrame("KL");

        int number = Integer.parseInt(String.valueOf(bankaccount.getCurrentMoney()));
        assertTrue(0 <= number && number<= 10000);
    }
}