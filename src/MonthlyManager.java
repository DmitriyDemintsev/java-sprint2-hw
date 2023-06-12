import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyManager {

    public HashMap<String, ArrayList<MonthlyReport>> statisticsForMonth = new HashMap<>();

    public void loadFile(String month, String path) {
        ArrayList<String> lines = new FileReader().readFileContents(path);
        ArrayList<MonthlyReport> statistics = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i); // получаем строку вида товар, доход/расход, кол-во, сумма
            String[] parts = line.split(","); // режем строку на части
            String item_name = parts[0];
            boolean is_expense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int unit_price = Integer.parseInt(parts[3]);

            MonthlyReport monthlyReport = new MonthlyReport(item_name, is_expense, quantity, unit_price, month);
            statistics.add(monthlyReport);
        }
        statisticsForMonth.put(month, statistics);
    }

    public void getMostProfitableProduct() { //считаем прибыльный товар
        for (String month : statisticsForMonth.keySet()) {
            ArrayList<MonthlyReport> statistics = statisticsForMonth.get(month);
            int sum = 0;
            int maxProfit = 0;
            String item_name = "";
            for (MonthlyReport report : statistics) {
                if (!report.is_expense) {
                    sum = report.unit_price * report.quantity;
                    if (sum > maxProfit) {
                        maxProfit = sum;
                        item_name = report.item_name;
                    }
                }
            }
            System.out.println("Данные за " + month);
            System.out.println("Самый прибыльный товар - " + item_name + ". Он принес " + sum + "руб.");
        }
    }

    public void getBiggestSpend() { // считаем макс трату
        for (String month : statisticsForMonth.keySet()) {
            ArrayList<MonthlyReport> statistics = statisticsForMonth.get(month);
            int sum = 0;
            int maxExpense = 0;
            String item_name = "";
            for (MonthlyReport report : statistics) {
                if (report.is_expense) {
                    sum = report.unit_price * report.quantity;
                    if (sum > maxExpense) {
                        maxExpense = sum;
                        item_name = report.item_name;
                    }
                }
            }
            System.out.println("Самая большая трата - " + item_name + " на " + sum + "руб.");

        }
    }
}
