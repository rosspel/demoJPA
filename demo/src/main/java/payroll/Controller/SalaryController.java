package payroll.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import payroll.Repository.SalaryRepository;

import java.sql.SQLException;

@RestController
public class SalaryController {

    @Autowired
    SalaryRepository salaryRepository;

    @DeleteMapping("/salaries/{id}")
    public @ResponseBody
    void delSalary(@PathVariable Long id) {
        try {
                salaryRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
