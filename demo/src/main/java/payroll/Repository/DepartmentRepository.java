package payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import payroll.Department;
import payroll.dto.DepartmentDTO;
import payroll.dto.EmployeeDTO;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value= "SELECT new payroll.dto.DepartmentDTO(d.depId, e.name) FROM Department d LEFT JOIN Employee e ON d.depId =e.depId", nativeQuery = false)
    public List<DepartmentDTO> OnetoMany();
}
