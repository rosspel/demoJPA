package payroll.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import payroll.Employee;
import payroll.dto.EmployeeDTO;
import payroll.dto.SumSalaryDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value= "SELECT e,s FROM Employee e LEFT JOIN Salary s ON e.id =s.employeeId", nativeQuery = false)
    public List<Employee> getJoinEmployees();

    @Query(value= "SELECT new payroll.dto.EmployeeDTO(e.id, e.name, e.role, COALESCE(s.amount,0)) FROM Employee e LEFT JOIN Salary s ON e.id =s.employeeId", nativeQuery = false)
    public List<EmployeeDTO> getJoinEmployeesTest();

    @Query(value = "SELECT new payroll.dto.SumSalaryDTO(e.depId, sum(s.amount)) FROM Employee e LEFT JOIN Salary s ON e.id =s.employeeId GROUP BY e.depId", nativeQuery = false)
    public List<SumSalaryDTO> getSumSalaries();

    @Query(value= "SELECT e FROM Employee e WHERE e.email =?1")
    public Optional<Employee> findEmployeeByEmail(String email);

}
