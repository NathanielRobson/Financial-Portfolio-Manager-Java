import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class GraphBuilderFrameTest {
    // this method checks if a csv has been download prior to the user downloading it.
    @Test
    public void main() {
        GraphBuilderFrame graph = new GraphBuilderFrame();
        File file = new File("teamproject-master/src/NFLX.csv");
        assertTrue(!file.isDirectory() && !file.canRead());
        //assertTrue(file.exists());
        //assertEquals(true, graph.getSymbolField().contains("APPL"));

    }
}