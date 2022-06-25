package payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private Integer locationId;

    @Column(name="address")
    private String address;

    @Column(name="postalCode")
    private Integer postalCode;

    @Column(name="city")
    private String city;

    @Column(name="province")
    private String province;

    @Column(name="country_id")
    private Integer countryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
    private Country country;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Department> departments;


    public Location() {};

    public Location(Integer locationId, String address, Integer postalCode, String city, String province, Integer countryId) {
        this.locationId = locationId;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.countryId = countryId;
    }


    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
