public class MonthlyReport {
    public String item_name;
    public boolean is_expense;
    public int quantity;
    public int unit_price;
    public String month;


    public MonthlyReport(String item_name, boolean is_expense, int quantity, int unit_price, String month) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.month = month;
    }

    public int countingExpensesAndProfits() {
        return unit_price * quantity;
    }
}
