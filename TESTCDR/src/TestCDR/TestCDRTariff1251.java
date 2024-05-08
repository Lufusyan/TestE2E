package TestCDR;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestCDRTariff1251 {
    private BufferedWriter writer;

    public void generateCDRFiles() {
        try {
            String fileName = "TestCDRTariff1251";
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
        int endTime = 1709851233;
        String cdr = String.format("%s, %d, %d, %d, %d",typeCall, callerNumber, calleeNumber, startTime, endTime);
        writer.write(cdr);
    }
    public static void main(String[] args) {
        TestCDRTariff1251 generator = new TestCDRTariff1251();
        generator.generateCDRFiles();
    }
}