import java.util.ArrayList;

public class Checker {
    public MonthlyManager monthlyManager;
    public YearlyManager yearlyManager;

    public Checker(MonthlyManager monthlyManager, YearlyManager yearlyManager) {
        this.monthlyManager = monthlyManager;
        this.yearlyManager = yearlyManager;
    }


    public boolean check() {
        for (YearlyReport yearlyReport : yearlyManager.statisticsForYear) {
            ArrayList<MonthlyReport> statistics = monthlyManager.statisticsForMonth.get(yearlyReport.month);
            int sum = 0;
            for (MonthlyReport monthlyReport : statistics) {
                if (monthlyReport.is_expense == yearlyReport.is_expense) {
                    sum += monthlyReport.quantity * monthlyReport.unit_price;
                }
            }
            if (yearlyReport.amount != sum) {
                System.out.println("Обнаружена ошибка сверки данных в месяце " + yearlyReport.month);
                return false;
            }
        }
        System.out.println("Операция успешно завершена.");
        return true;
    }
}
