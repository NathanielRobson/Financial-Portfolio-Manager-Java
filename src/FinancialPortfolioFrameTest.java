import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FinancialPortfolioFrameTest {
    // this method checks if the file exist in the repository and if it can be read
    @Test
    public void main() throws Exception {
        File file = new File("M:\\CE291 - Team Project\\teamproject\\UserPortfolios");
        assertTrue(file.isDirectory() && file.canRead());
    }
}