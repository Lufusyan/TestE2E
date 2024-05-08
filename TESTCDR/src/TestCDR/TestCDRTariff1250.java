package TestCDR;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestCDRTariff1250 {
    private BufferedWriter writer;

    public void generateCDRFiles() {
        try {
            String fileName = "TestCDRTariff1250";
            writer = new BufferedWriter(new FileWriter(fileName + ".txt"));
            generateCDR();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateCDR() throws IOException {
        String typeCall = "01";
        long callerNumber = 73735535243L;
        long calleeNumber = 73734435000L;
        int startTime = 1709798657;
        int endTime = 1709799601;
        String cdr = String.format("%s, %d, %d, %d, %d",typeCall, callerNumber, calleeNumber, startTime, endTime);
        writer.write(cdr);
    }
    public static void main(String[] args) {
        TestCDRTariff1250 generator = new TestCDRTariff1250();
        generator.generateCDRFiles();
    }
}