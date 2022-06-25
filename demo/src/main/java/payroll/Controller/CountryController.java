package payroll.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import payroll.Country;
import payroll.Repository.CountryRepository;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @PostMapping(path="countries/add")
    public void addNewCountry (@RequestBody Country country) {
        try {
            countryRepository.save(country);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
