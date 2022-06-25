package payroll.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.Department;
import payroll.Employee;
import payroll.EmployeeNotFoundException;
import payroll.Repository.EmployeeRepository;
import payroll.Repository.SalaryRepository;
import payroll.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import payroll.dto.DepartmentDTO;
import payroll.dto.EmployeeDTO;
import payroll.dto.HttpResponse;
import payroll.dto.SumSalaryDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class EmployeeController {

    @Autowired //per far comunicare Controller con Services
    private EmployeeService _employeeService;


    @GetMapping(path="employees/welcome")
    public @ResponseBody String welcomePage() {
        return _employeeService.welcome();
    }

   /* @GetMapping(path="/employees/test")
    public @ResponseBody List<Employee> all2() {
        return _employeeService.getAllEmployee2();
    }*/

    @GetMapping(path="/employees/test2")
    public @ResponseBody HttpResponse<List<EmployeeDTO>> all2() {
        HttpResponse<List<EmployeeDTO>> response = new HttpResponse<List<EmployeeDTO>>();

        try {
            List<EmployeeDTO> employeeDTOList = _employeeService.getJoin();
            response.setStatus("SUCCESS");
            response.setDate(new Date());
            response.setData(employeeDTOList);
            response.setError(null);
        } catch(Exception e) {
            response.setStatus("ERROR");
            response.setDate(new Date());
            response.setData(null);
            response.setError(e.getMessage());
        }
        return response;
    }

    @GetMapping(path= "/employees/departments")
    public @ResponseBody HttpResponse<List<DepartmentDTO>> departments() {
        HttpResponse<List<DepartmentDTO>> response = new HttpResponse<List<DepartmentDTO>>();

        try {
            List<DepartmentDTO> departmentDTOList= _employeeService.getOnetoMany();
            response.setStatus("SUCCESS");
            response.setDate(new Date());
            response.setData(departmentDTOList);
            response.setError(null);
        } catch(Exception e) {
            response.setStatus("ERROR");
            response.setDate(new Date());
            response.setData(null);
            response.setError(e.getMessage());
        }
        return response;
    }

   @GetMapping(path="/employees/sum")
    public @ResponseBody HttpResponse<List<SumSalaryDTO>> salaries() {
       HttpResponse<List<SumSalaryDTO>> response = new HttpResponse<List<SumSalaryDTO>>();

       try {
           List<SumSalaryDTO> sumSalaryDTOList= _employeeService.getSalary();
           response.setStatus("SUCCESS");
           response.setDate(new Date());
           response.setData(sumSalaryDTOList);
           response.setError(null);
       } catch(Exception e) {
           response.setStatus("ERROR");
           response.setDate(new Date());
           response.setData(null);
           response.setError(e.getMessage());
       }
       return response;}

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/employees")
    public @ResponseBody List<Employee> all() {
        return _employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee oneEmployee (@PathVariable Long id) {
        return _employeeService.one(id);
    }

    @PostMapping(path="employees/add")
    public @ResponseBody String addNewEmployee (@RequestBody Employee newEmployee) {
        return _employeeService.insertEmployee(newEmployee);
    }

    @DeleteMapping("/employees/{id}")

    public ResponseEntity<HttpResponse<Boolean>> delEmployee(@PathVariable Long id) {
        ResponseEntity responseEntity;
        HttpResponse<Boolean> response = new HttpResponse<Boolean>();
        try {
            _employeeService.deleteEmployee(id);
            response.setStatus("SUCCESS");
            response.setDate(new Date());
            response.setData(true);
            response.setError(null);
            responseEntity = new ResponseEntity<HttpResponse<Boolean>>(response, HttpStatus.OK);

        } catch(Exception e) {
            response.setStatus("ERROR");
            response.setDate(new Date());
            response.setData(false);
            response.setError(e.getMessage());
            responseEntity = new ResponseEntity<HttpResponse<Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/employees/{id}")
    public void upEmployee( @PathVariable Long id, @RequestBody Employee newEmployee) {
        _employeeService.updateEmployee(id, newEmployee);
    }

    /*@GetMapping("/employees")
    List<Employee> getEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> result = _employeeService.getAllEmployee();
        return result;
    }*/

    /*@GetMapping("/employees/join")
    HttpResponse<List<EmployeeDTO>> join() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> resultJoin = _employeeService.getJoin();
        HttpResponse<List<EmployeeDTO>> response = new HttpResponse<List<EmployeeDTO>>();
        response.setStatus("SUCCESS");
        response.setDate(new Date());
        response.setData(resultJoin);
        return response;
    }


   @PutMapping("/employees/{id}")
   HttpResponse<Integer> upEmployee(@RequestBody Employee employee, @PathVariable Long id) {
       HttpResponse<Integer> response = new HttpResponse<Integer>();
       Integer result = 0;
       try {
           result = _employeeService.updateEmployee(employee,id);

           if(result == 0){
               throw new SQLException("Entity with id "+ id +" not Found");
           }

           response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
           response.setDate(new Date());
           response.setData(result);

       } catch (ClassNotFoundException e) {
           response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
           response.setDate(new Date());
           response.setData(result);
           response.setError(e.getMessage());
       } catch (SQLException e) {
           response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
           response.setDate(new Date());
           response.setData(result);
           response.setError(e.getMessage());
       } catch (Exception e) {
           response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
           response.setDate(new Date());
           response.setData(result);
           response.setError(e.getMessage());
       }

       return response;
   }


    @DeleteMapping("/employees/{id}")
    HttpResponse<Integer> deleteEmployee(@PathVariable Long id) {
        HttpResponse<Integer> response = new HttpResponse<Integer>();
        Integer result = 0;
        try {
            result = _employeeService.delEmployee(id);

            if(result == 0){
                throw new SQLException("Entity with id "+ id +" not Found");
            }

            response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
            response.setDate(new Date());
            response.setData(result);

        } catch (ClassNotFoundException e) {
            response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
            response.setDate(new Date());
            response.setData(result);
            response.setError(e.getMessage());
        } catch (SQLException e) {
            response.setStatus(result == 1 ? "SUCCESS" : "ERROR");
            response.setDate(new Date());
            response.setData(result);
            response.setError(e.getMessage());
        }

        return response;
    } */

}

