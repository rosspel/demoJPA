package payroll.dto;

import lombok.Data;
import payroll.Employee;
import payroll.Salary;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeeDTO {

    private  Long Id;

    private String Name;

    private String Role;

    private payroll.Salary Salary;

    public EmployeeDTO(Long id, String name, String role, int amount){
        this.Id =id;
        this.Name =name;
        this.Role =role;
        this.Salary = new Salary(id,amount);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public payroll.Salary getSalary() {
        return Salary;
    }

    public void setSalary(payroll.Salary salary) {
        Salary = salary;
    }
}
