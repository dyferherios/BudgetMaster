package school.hei;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Expense exp0 = new Expense("Mofo gasy",10.0,Categories.Food_and_catering, LocalDate.now());
        Expense exp1 = new Expense("Hofatrano",100.0,Categories.Food_and_catering, LocalDate.now());
        Expense exp2 = new Expense("Nandeha nianatra",500.0,Categories.Transportation, LocalDate.of(2024,9,21));
        User user0= new User("freddy",10000.0, List.of(exp0, exp1));
        user0.addExpense(exp2);
        System.out.println(user0);
        System.out.println(user0.getExpensesByCategory(Categories.Food_and_catering));
        System.out.println(user0.getTotalSpentThisMonth());
        System.out.println(user0.getRemainingBudget());
        System.out.println(user0.getTopCategories());
        System.out.println(user0.calculateAverageSpendingPerCategory());
    }
}