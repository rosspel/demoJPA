package payroll.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import payroll.Employee;
import payroll.EmployeeNotFoundException;
import payroll.Repository.DepartmentRepository;
import payroll.Repository.EmployeeRepository;
import payroll.Repository.SalaryRepository;
import payroll.dto.DepartmentDTO;
import payroll.dto.EmployeeDTO;
import payroll.dto.SumSalaryDTO;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    private static final Logger log = Logger.getLogger(EmployeeService.class.getName());

    public String welcome() {
        String html = "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Welcome\n" + "</body>\n" + "</html>";
        return html;
    }

    public List<Employee> getAllEmployee2() {
        return employeeRepository.getJoinEmployees();
    }

    public List<Employee> getAllEmployee() {

        return employeeRepository.getJoinEmployees();
    }


    public List<EmployeeDTO> getJoin() {
        return employeeRepository.getJoinEmployeesTest();
    }

    public List<DepartmentDTO> getOnetoMany() {return departmentRepository.OnetoMany();}

    public List<SumSalaryDTO> getSalary() { return employeeRepository.getSumSalaries();}

    @PrePersist
    @PostPersist
    public String insertEmployee(Employee newEmployee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(newEmployee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        employeeRepository.save(newEmployee);
        log.info("Going to add a new employee");
        log.info("New Employee added");
        return "Employee " + newEmployee.getName() + " has been saved " + "with id: " + newEmployee.getId();
    }


    public String deleteEmployee(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Employee with id " + id + " does not exist");
        }
        else if(id == null) {
            throw new IllegalArgumentException("Parametro id null!!");
        }
        employeeRepository.deleteById(id);
        return "Employee with id: " + id + " has been deleted";

    }

    @Transactional
    public Employee updateEmployee(Long id, Employee n) {
        return employeeRepository.findById(id).map(employee -> {
                    employee.setName(n.getName());
                    employee.setRole(n.getRole());
                    employee.setEmail(n.getEmail());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee one(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }



   /* public List<Long> getEmployeeByName(String name) {
        List<Employee> EmployeeList = new ArrayList<Employee>();
        EmployeeList.add(new Employee(1L,"pippo","developer"));
        EmployeeList.add(new Employee(2L,"pluto","analyst"));

        List<Long> test = EmployeeList.stream().filter(n -> n.getName().equals(name)).map(n -> n.getId()).collect(Collectors.toList());


        return test;
    }*/

    /*public List<String> getEmployeeById(Long id) {
        List<Employee> EmployeeList = new ArrayList<Employee>();
        EmployeeList.add(new Employee(1L,"pippo","developer"));
        EmployeeList.add(new Employee(2L,"pluto","analyst"));

        List<String> test = EmployeeList.stream().filter(n -> n.getId().equals(id)).map(n -> n.getName()).collect(Collectors.toList());
        return test;
       }*/

       /*private EmployeeRepository employeeRepository;
       public EmployeeService(EmployeeRepository employeeRepository) {
           super();
           this.employeeRepository = employeeRepository;
       }
       public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
       }*/

     //DATABASE CONNECTION WITH JDBC
   /* public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        //DatabaseConnection db = new DatabaseConnection();
        ResultSet rs = db.ExecuteQuery("SELECT * from employees;");
        List<Employee> employeeList = new ArrayList<Employee>();
        while (rs.next()) {
            employeeList.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("role")));
        }
        db.CloseConnection();
        return employeeList;
    }

    public List<EmployeeDTO> getJoin() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        //DatabaseConnection db = new DatabaseConnection();
        ResultSet rs = db.ExecuteQuery("SELECT id, name, role, amount FROM employees INNER JOIN salaries ON employees.id = salaries.employee_id;");
        while (rs.next()) {
            employeeList.add(new EmployeeDTO(rs.getLong("id"), rs.getString("name"), rs.getString("role"), rs.getInt("amount")));
        }
        db.CloseConnection();
        return employeeList;
    }


    public void insertEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        //DatabaseConnection db = new DatabaseConnection();
        PreparedStatement ps = db.QueryUpdate("INSERT INTO employees(name,role) VALUES(?,?);");
        ps.setString(1,employee.getName());
        ps.setString(2,employee.getRole());
        ps.executeUpdate();
        db.CloseConnection();
    }

    public Integer updateEmployee(Employee employee, Long id) throws ClassNotFoundException, SQLException {
        //DatabaseConnection db = new DatabaseConnection();
        PreparedStatement ps = db.QueryUpdate("UPDATE employees SET name=? , role=? WHERE id=? ;");
        ps.setString(1,employee.getName());
        ps.setString(2,employee.getRole());
        ps.setInt(3,employee.getId().intValue());
        Integer result = ps.executeUpdate();
        db.CloseConnection();
        return result;
    }

    public Integer delEmployee(Long id) throws ClassNotFoundException, SQLException {
        //DatabaseConnection db = new DatabaseConnection();
        PreparedStatement ps = db.QueryUpdate("DELETE FROM employees WHERE id = ?;");
        ps.setInt(1, id.intValue());
        Integer result = ps.executeUpdate();
        db.CloseConnection();
        return result;
    }*/


}
