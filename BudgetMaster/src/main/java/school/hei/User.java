package school.hei;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Getter @Setter @ToString
public class User {
    private String name;
    private double BudgetPerMonth;
    private List<Expense> expenseLists;

    public User(String name, Double budgetPerMonth, List<Expense> expenseLists) {
        this.name = name;
        this.BudgetPerMonth = budgetPerMonth;
        this.expenseLists = new ArrayList<>();
        for (Expense expense : expenseLists) {
            if (expense.getAmount() > 0.0) {
                this.expenseLists.add(expense);
            } else {
                System.out.println("Expense with amount " + expense.getAmount() + " is not added because it is not positive.");
            }
        }
    }

    public void addExpense(Expense e){
        expenseLists.add(e);
    }

    public List<Expense> getExpensesByCategory(Categories category){
        return this.expenseLists
                .stream()
                .filter(expense -> expense.getCategory().equals(category))
                .toList();
    }

    public double getTotalSpentThisMonth(){
        List<Expense> ExpenseFilteredByDate = this.expenseLists
                .stream()
                .filter(expense -> expense.getDate().getYear() == LocalDate.now().getYear())
                .filter(expense -> expense.getDate().getMonth().equals(LocalDate.now().getMonth()))
                .toList();

        return ExpenseFilteredByDate
                .stream()
                .map(Expense::getAmount)
                .reduce(0.0, Double::sum);
    }

    public double getRemainingBudget(){
        double totalSpentPerMonth = this.getTotalSpentThisMonth();
        return this.getBudgetPerMonth() - totalSpentPerMonth;
    }

    public List<Categories> getTopCategories(){
        HashMap<Categories, Double> categoriesDoubleHashMap = new HashMap<>();
        for (Categories category : Categories.values()) {
            List<Expense> ExpenseByCategory = this.expenseLists
                    .stream()
                    .filter(expense -> expense.getCategory() == category)
                    .toList();
            Double total = ExpenseByCategory.stream().map(Expense::getAmount).reduce(0.0, Double::sum);
            categoriesDoubleHashMap.put(category, total);
        }

        Map<Categories, Double> sortedExpenses = categoriesDoubleHashMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Categories, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        return sortedExpenses.keySet()
                .stream()
                .limit(3)
                .toList();
    }

    public HashMap<Categories, Double> calculateAverageSpendingPerCategory(){
        HashMap<Categories, Double> categoriesDoubleHashMap = new HashMap<>();
        for (Categories category : Categories.values()) {
            List<Expense> ExpenseByCategory = this.expenseLists
                    .stream()
                    .filter(expense -> expense.getCategory() == category)
                    .toList();
            Double total = ExpenseByCategory.stream().map(Expense::getAmount).reduce(0.0, Double::sum);
            categoriesDoubleHashMap.put(category, Double.isNaN(total / ExpenseByCategory.size()) ? 0.0 : total/ExpenseByCategory.size());
        }
        return categoriesDoubleHashMap;
    }

}
