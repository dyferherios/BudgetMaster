package school.hei;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @ToString
public class Expense {
    private String description;
    private double amount;
    private Categories category;
    private LocalDate date;
}
