package TestCDR;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestCDRCallerNumber2DifferentOperator {
    private BufferedWriter writer;

    public void generateCDRFiles() {
        try {
            String fileName = "TestCDRCallerNumber2DifferentOperator";
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
        long callerNumber = 73734435243L;
        long calleeNumber = 73734435000L;
        int startTime = 1709798657;
        int endTime = 1709799601;
        String cdr = String.format("%s, %d, %d, %d, %d",typeCall, callerNumber, calleeNumber, startTime, endTime);
        writer.write(cdr);
    }
    public static void main(String[] args) {
        TestCDRCallerNumber2DifferentOperator generator = new TestCDRCallerNumber2DifferentOperator();
        generator.generateCDRFiles();
    }
}