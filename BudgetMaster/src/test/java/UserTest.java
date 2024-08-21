import org.junit.jupiter.api.Test;
import school.hei.Categories;
import school.hei.Expense;
import school.hei.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    Expense exp0 = new Expense("Mofo gasy", 10.0, Categories.Food_and_catering, LocalDate.now());
    Expense exp1 = new Expense("Hofatrano", 100.0, Categories.Food_and_catering, LocalDate.now());
    Expense exp2 = new Expense("Nandeha nianatra", 500.0, Categories.Transportation, LocalDate.of(2024, 9, 21));

    User user0 = new User("freddy", 10000.0, List.of(exp0, exp1));


    @Test
    public void testAddExpense() {
        user0.addExpense(exp2);
        assertTrue(user0.getExpenseLists().contains(exp2), "Expense should be added to the user's expense list.");
    }

    @Test
    public void testGetExpensesByCategory() {
        List<Expense> foodExpenses = user0.getExpensesByCategory(Categories.Food_and_catering);
        assertEquals(2, foodExpenses.size(), "There should be 2 food expenses.");
        assertTrue(foodExpenses.contains(exp0), "The expense exp0 should be in the food expenses list.");
        assertTrue(foodExpenses.contains(exp1), "The expense exp1 should be in the food expenses list.");
    }

    @Test
    public void testGetTotalSpentThisMonth() {
        user0.addExpense(new Expense("Vary", 50.0, Categories.Food_and_catering, LocalDate.now()));
        double totalSpent = user0.getTotalSpentThisMonth();
        assertEquals(160.0, totalSpent, "The total spent this month should be 160.0.");
    }

    @Test
    public void testGetRemainingBudget() {
        user0.addExpense(new Expense("vary", 50.0, Categories.Food_and_catering, LocalDate.now()));
        double remainingBudget = user0.getRemainingBudget();
        assertEquals(10000.0 - 160.0, remainingBudget, "The remaining budget should be 10000.0 - 160.0.");
    }

    @Test
    public void testGetTopCategories() {
        user0.addExpense(exp2);

        List<Categories> topCategories = user0.getTopCategories();

        assertNotNull(topCategories, "Top categories should not be null.");
        assertTrue(topCategories.contains(Categories.Food_and_catering), "Food_and_catering should be one of the top categories.");
        assertTrue(topCategories.contains(Categories.Transportation), "Transportation should be one of the top categories.");
    }

    @Test
    public void testCalculateAverageSpendingPerCategory() {
        user0.addExpense(exp2);
        Map<Categories, Double> averageSpending = user0.calculateAverageSpendingPerCategory();
        assertTrue(averageSpending.containsKey(Categories.Food_and_catering), "Average spending should be calculated for Food_and_catering.");
        assertTrue(averageSpending.containsKey(Categories.Transportation), "Average spending should be calculated for Transportation.");
    }
}
