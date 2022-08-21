package in.mustaq.expensetrackerapi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.mustaq.expensetrackerapi.entity.Expense;

public interface ExpenseService {

	public Page<Expense> getAllExpenses(Pageable page);

	public Expense getExpenseById(Long id);

	public void deleteExpenseId(Long id);

	public Expense saveExpenseDetails(Expense expense);

	public Expense updateExpenseDetails(Long id, Expense expense);

	public List<Expense> readByCategory(String category, Pageable page);
	
	public List<Expense> readByName(String keyword, Pageable page);
	
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page);

}
