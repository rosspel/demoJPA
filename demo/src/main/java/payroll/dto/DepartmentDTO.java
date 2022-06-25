package payroll.dto;

public class DepartmentDTO {

    private Integer depId;

    private String name;


    public DepartmentDTO(Integer depId, String name) {
        this.depId = depId;
        this.name = name;
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
}
