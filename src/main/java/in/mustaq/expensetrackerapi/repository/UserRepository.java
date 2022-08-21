package in.mustaq.expensetrackerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mustaq.expensetrackerapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);

}
