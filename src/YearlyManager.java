import java.util.ArrayList;
import java.util.HashMap;

public class YearlyManager {


    public ArrayList<YearlyReport> statisticsForYear = new ArrayList<>();

    public YearlyManager(String path) {
        ArrayList<String> lines = new FileReader().readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i); // получаем строку вида месяц, сумма, доход/расход
            String[] parts = line.split(","); // режем строку на части
            String month = parts[0];
            int amount = Integer.parseInt(parts[1]);
            boolean is_expense = Boolean.parseBoolean(parts[2]);

            YearlyReport yearlyReport = new YearlyReport(month, amount, is_expense);
            statisticsForYear.add(yearlyReport);
        }
        // System.out.println("");
    }


    public void getAverageIncome() { // считаем средний доход
        int sum = 0;
        int count = 0;
        for (YearlyReport report : statisticsForYear) {
            if (report.is_expense) {
                sum += report.amount;
                count++;
            }
        }
        System.out.println("Средний доход по всем операциям составил: " + sum / count + " руб.");
    }

    public void getAverageExpenses() { // считаем средний расход
        int sum = 0;
        int count = 0;
        for (YearlyReport report : statisticsForYear) {
            if (!report.is_expense) {
                sum += report.amount;
                count++;
            }
        }
        System.out.println("Средний расход по всем операциям составил: " + sum / count + " руб.");
    }

    public void getProfitStatement() { // считаем прибыль
        HashMap<String, Integer> profitStatement = new HashMap<>();
        for (YearlyReport report : statisticsForYear) {
            int content = profitStatement.getOrDefault(report.month, 0);
            if (report.is_expense) {
                content -= report.amount;
            } else {
                content += report.amount;
            }
            profitStatement.put(report.month, content);
        }
        for (String month : profitStatement.keySet()) {
            System.out.println("Прибыль за месяц " + month + " составила " + profitStatement.get(month) + " руб.");
        }
    }

}
