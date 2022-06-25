package payroll.dto;

public class SumSalaryDTO {

    private Integer depId;

    private Integer sumSalary;

    public SumSalaryDTO() {};

    public SumSalaryDTO(Integer depId, Long sumSalary) {
        this.depId = depId;
        this.sumSalary = sumSalary.intValue();
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getSumSalary() {
        return sumSalary;
    }

    public void setSumSalary(Integer sumSalary) {
        this.sumSalary = sumSalary;
    }
}


