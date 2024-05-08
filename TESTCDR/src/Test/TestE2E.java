package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestE2E {
    @Test
    public void testCallAndBillingForClassicTariff_IncomingCall() {
        // Создаем запись CDR для входящего звонка с "Классическим" тарифом
        String cdrRecord = readCDRFromFile("TestCDRSuccessfulIncomingCall.txt");

        // Проверяем, что баланс не изменился после входящего звонка для "Классического" тарифа
        assertEquals(100.0, checkBillingAfterCall(cdrRecord, "11"), 0.01);
    }

    @Test
    public void testCallAndBillingForClassicTariff_OutgoingCallToSameOperator() {
        // Создаем запись CDR для исходящего звонка на абонента того же оператора с "Классическим" тарифом
        String cdrRecord = readCDRFromFile("TestCDRSuccessful.txt");

        // Проверяем, что корректно списываются средства после исходящего звонка на абонента того же оператора для "Классического" тарифа
        assertEquals(98.5, checkBillingAfterCall(cdrRecord, "11"), 0.01);
    }

    @Test
    public void testCallAndBillingForClassicTariff_OutgoingCallToDifferentOperator() {
        // Создаем запись CDR для исходящего звонка на абонента другого оператора с "Классическим" тарифом
        String cdrRecord = readCDRFromFile("TestCDRCallerNumber2DifferentOperator.txt");

        // Проверяем, что корректно списываются средства после исходящего звонка на абонента другого оператора для "Классического" тарифа
        assertEquals(97.5, checkBillingAfterCall(cdrRecord, "11"), 0.01);
    }

    @Test
    public void testCallAndBillingForMonthlyTariff_First50Minutes() {
        // Создаем запись CDR для звонка на "Помесячном" тарифе в пределах первых 50 минут
        String cdrRecord = readCDRFromFile("TestCDRTariff1250.txt");

        // Проверяем, что баланс не изменился после звонка в пределах первых 50 минут для "Помесячного" тарифа
        assertEquals(100.0, checkBillingAfterCall(cdrRecord, "12"), 0.01);
    }

    @Test
    public void testCallAndBillingForMonthlyTariff_After50Minutes() {
        // Создаем запись CDR для звонка на "Помесячном" тарифе после истечения первых 50 минут
        String cdrRecord = readCDRFromFile("TestCDRTariff1251.txt");

        // Проверяем, что корректно списываются средства после исходящего звонка на "Помесячном" тарифе после истечения первых 50 минут
        assertEquals(95.0, checkBillingAfterCall(cdrRecord, "12"), 0.01);
    }

    private double checkBillingAfterCall(String cdrRecord, String tariff) {
        // Имитируем вызов метода BRTService.chargeCallCost для списания средств
        double callCost = BRTService.chargeCallCost(cdrRecord, tariff);

        // Имитируем вызов метода BRTService.getBalance для получения текущего баланса
        double balance = BRTService.getBalance();

        // Возвращаем текущий баланс после звонка
        return balance;
    }

    private String readCDRFromFile(String filePath) {
        StringBuilder cdrRecord = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Читаем файл построчно
            while ((line = reader.readLine()) != null) {
                // Добавляем каждую строку в cdrRecord с разделителем ","
                cdrRecord.append(line).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Удаляем последний символ - лишнюю запятую
        cdrRecord.deleteCharAt(cdrRecord.length() - 1);
        return cdrRecord.toString();
    }
}