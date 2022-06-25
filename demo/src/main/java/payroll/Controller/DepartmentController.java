package payroll.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import payroll.Department;
import payroll.Employee;
import payroll.Repository.DepartmentRepository;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping(path="departments/add")
    public void addNewDepartment (@RequestBody Department department) {
        try {
            departmentRepository.save(department);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @DeleteMapping(path="departments/{id}")
    public void deldepartment(@PathVariable Integer id) {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}
