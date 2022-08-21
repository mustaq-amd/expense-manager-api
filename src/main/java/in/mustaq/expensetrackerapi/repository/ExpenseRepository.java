package in.mustaq.expensetrackerapi.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mustaq.expensetrackerapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	// SELECT * FROM expenses WHERE category=?
	public Page<Expense> findByCategory(String category, Pageable page);

	// SELECT * FROM expenses WHERE name LIKE '%keyword%'
	public Page<Expense> findByNameContaining(String keyword, Pageable page);

	// SELECT * FROM expenses WHERE date between 'startDate' and 'endDate'
	public Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
	

	public Page<Expense> findByUserId(Long userId, Pageable page);

	public Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
	
	// SELECT * FROM expenses WHERE userId=? And category=?
	public Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

	// SELECT * FROM expenses WHERE userId=? And name LIKE '%keyword%'
	public Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);

	// SELECT * FROM expenses WHERE userId=?  date between 'startDate' and 'endDate'
	public Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

}
