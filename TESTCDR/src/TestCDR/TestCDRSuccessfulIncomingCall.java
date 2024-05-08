import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class TestCDRSuccessfulIncomingCall {
    private BufferedWriter writer;

    public void generateCDRFiles() {
        try {
            String fileName = "TestCDRSuccessfulIncomingCall";
            writer = new BufferedWriter(new FileWriter(fileName + ".txt"));
            generateCDR();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateCDR() throws IOException {
        String typeCall = "02";
        long callerNumber = 73734435243L;
        long calleeNumber = ThreadLocalRandom.current().nextLong(70000000000L, 80000000000L);
        int startTime = 1709798657;
        int endTime = 1709799601;
        String cdr = String.format("%s, %d, %d, %d, %d",typeCall, callerNumber, calleeNumber, startTime, endTime);
        writer.write(cdr);
    }
    public static void main(String[] args) {
        TestCDRSuccessfulIncomingCall generator = new TestCDRSuccessfulIncomingCall();
        generator.generateCDRFiles();
    }
}

