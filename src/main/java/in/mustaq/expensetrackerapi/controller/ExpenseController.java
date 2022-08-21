package in.mustaq.expensetrackerapi.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.mustaq.expensetrackerapi.entity.Expense;
import in.mustaq.expensetrackerapi.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	

	@GetMapping
	public List<Expense> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}

	@GetMapping("/{id}")
	public Expense getExpenseById(@PathVariable("id") Long id) {
		return expenseService.getExpenseById(id);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping
	public void deleteExpenseById(@RequestParam("id") Long id) {
		expenseService.deleteExpenseId(id);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {

		return expenseService.saveExpenseDetails(expense);

	}

	@PutMapping("/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
		return expenseService.updateExpenseDetails(id, expense);

	}

	@GetMapping("/category")
	public List<Expense> getAllExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readByCategory(category, page);

	}

	@GetMapping("/name")
	public List<Expense> getAllExpensesByName(@RequestParam String keyword, Pageable page) {
		return expenseService.readByName(keyword, page);

	}

	@GetMapping("/date")
	public List<Expense> getAllExpensesBetweenDates(@RequestParam(required = false) Date startDate,
			@RequestParam(required = false) Date endDate, Pageable page) {
		return expenseService.readByDate(startDate, endDate, page);

	}

}
