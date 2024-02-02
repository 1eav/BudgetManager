package org.example.expense;

import org.example.expense.dto.Expense;
import org.example.expense.dto.Incomes;
import org.example.expense.dto.TransactionReport;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetManagerTest {

    @Test
    public void testAddExpense() {
        BudgetManager manager = new BudgetManager();
        Expense expense = new Expense(50.0, "category", "test");
        manager.addExpense(expense);

        assertEquals(new BigDecimal("50.0"), manager.getTotalExpenses());
    }

    @Test
    public void testAddIncome() {
        BudgetManager manager = new BudgetManager();
        Incomes income = new Incomes(100.0, "Salary", "test");
        manager.addIncomes(income);

        assertEquals(new BigDecimal("100.0"), manager.getTotalIncomes());
    }

    @Test
    public void testAverageIncome() {
        BudgetManager manager = new BudgetManager();
        Incomes income1 = new Incomes(100.0, "Salary", "test");
        Incomes income2 = new Incomes(50.0, "Freelance", "test");

        manager.addIncomes(income1);
        manager.addIncomes(income2);

        assertEquals(new BigDecimal("75.0"), manager.averageIncome().get());
    }

    @Test
    public void testGetBalance() {
        BudgetManager manager = new BudgetManager();
        Incomes income = new Incomes(100.0, "Salary", "test");
        Expense expense = new Expense(50.0, "Lanch", "category");

        manager.addIncomes(income);
        manager.addExpense(expense);

        assertEquals(new BigDecimal("50.0"), manager.getBalance());
    }

    @Test
    public void testGenerateTransactionReport() {
        BudgetManager manager = new BudgetManager();
        Incomes income = new Incomes(100.0, "Salary", "test");
        Expense expense = new Expense(50.0, "category", "main");
        manager.addIncomes(income);
        manager.addExpense(expense);

        TransactionReport report = new TransactionReport(manager.getExpenses(), manager.getIncomes());

        assertEquals(1, report.getExpenses().size());
        assertEquals(1, report.getIncomes().size());
    }
}