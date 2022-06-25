package payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="salaries")
public class Salary {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name="amount")
    private int amount;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable=false, updatable=false)
    private Employee employee;

    @OneToOne(mappedBy = "salary")
    private Employee employee1;

    public Salary() {};

    public Salary(Long employeeId, int amount) {
        this.employeeId = employeeId;
        this.amount = amount;
    }

    public Long getEmployeeId() {
        return this.employeeId;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setEmployeeId(Long employee_id) {
        this.employeeId = employee_id;
    }

    public void setAmount(int amount) {
        this.amount= amount;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.employeeId + ", salary='" + this.amount +'}';
    }
}

