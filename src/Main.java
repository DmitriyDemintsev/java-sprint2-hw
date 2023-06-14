import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        YearlyManager yearlyManager = null;
        MonthlyManager monthlyManager = null;


        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                monthlyManager = new MonthlyManager();
                monthlyManager.loadFile("01", "m.202101.csv");
                monthlyManager.loadFile("02", "m.202102.csv");
                monthlyManager.loadFile("03", "m.202103.csv");
                if (!monthlyManager.checkAvailabilityOfData()) {
                    continue;
                }
                System.out.println("Данные успешно считаны.");
            } else if (command == 2) {
                yearlyManager = new YearlyManager("y.2021.csv");
                if (!yearlyManager.checkAvailabilityOfData()) {
                    continue;
                }
                System.out.println("Данные успешно считаны.");
            } else if (command == 3) {
                if (monthlyManager == null || !monthlyManager.checkAvailabilityOfData() ||
                        yearlyManager == null || !yearlyManager.checkAvailabilityOfData()) {
                    System.out.println("Нет или недостаточно данных для сверки отчётов. Убедитесь, что считаны все необходимые файлы.");
                } else {
                    Checker checker = new Checker(monthlyManager, yearlyManager);
                    checker.check();
                }
            } else if (command == 4) {
                if (monthlyManager == null || !monthlyManager.checkAvailabilityOfData()) {
                    System.out.println("Нет данных для отчёта. Возможно, файлы отсутствуют в нужной директории или повреждены.");
                    continue;
                }
                monthlyManager.printMostProfitableProduct();
                monthlyManager.printBiggestSpend();
            } else if (command == 5) {
                if (yearlyManager == null || !yearlyManager.checkAvailabilityOfData()) {
                    System.out.println("Нет данных для отчёта. Возможно, файлы отсутствуют в нужной директории или повреждены.");
                    continue;
                }
                System.out.println("Рассматриваемый год - 2021.");
                yearlyManager.printProfitStatement();
                yearlyManager.printAverageExpenses();
                yearlyManager.printAverageIncome();
            } else if (command == 0) {
                System.out.println("Введите команду для завершения работы:");
                String commandEnd = scanner.next();
                if (commandEnd.equals("key@11_06_2023")) {
                    System.out.println("Выход");
                    break;
                } else {
                    System.out.println("Неверная команда для завершения работы.");
                }
            } else {
                System.out.println("Такой команды не существует");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить годовой и месячные отчёты");
        System.out.println("4 - Показать информацию обо всех месячных отчётах");
        System.out.println("5 - Показать информацию о годовом отчёте");
        System.out.println("0 - Завершить работу");
    }
}
