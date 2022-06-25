package payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dep_id")
    private Integer depId;
    @Column(name="name")
    private String name;
    @Column(name = "location_id")
    private Integer locationId;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Employee> employees;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false, insertable = false, updatable = false)
    private Location location;

    public Department() {};

    public Department(Integer depId, String name, Integer locationId ) {
        this.depId = depId;
        this.name = name;
        this.locationId = locationId;
    }


    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", name='" + name + '\'' +
                ", locationId=" + locationId +
                ", employees=" + employees +
                '}';
    }
}
