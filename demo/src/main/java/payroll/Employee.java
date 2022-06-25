package payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Data
@Table(name="employees")
//@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private  Long id;
    @Column(name="name")
    private String name;
    @Column(name="role")
    private String role;
    @Column(name="dep_id")
    private Integer depId;
    @Column(name="birthdate")
    private LocalDate birthdate;
    @Transient
    private Integer age;
    @Column(name="email")
    private String email;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dep_id", nullable = false, insertable = false, updatable = false)
    private Department department;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Salary salary;


    public Employee() {}

    public Employee(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Employee(Long id, String name, String role, Integer depId ) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.depId = depId;
    }

    public Employee(String name, String role, Integer depId, LocalDate birthdate, String email) {
        this.name = name;
        this.role = role;
        this.depId = depId;
        this.birthdate = birthdate;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return Period.between(this.birthdate,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }

}

