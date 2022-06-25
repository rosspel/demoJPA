package payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payroll.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
