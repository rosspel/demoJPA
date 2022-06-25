package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payroll.Repository.*;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabaseDep(DepartmentRepository repository) {

        return args -> {
            repository.save(new Department(1,"Sviluppo",1));
            repository.save(new Department(2,"Risorse Umane",2));
            repository.save(new Department(3,"Vendite",2));
            log.info("Departments loaded");
        };
    }

   /* @Bean
    CommandLineRunner initDatabaseSal(SalaryRepository repository) {

        return args -> {
            repository.save(new Salary(1L,1500));
            repository.save(new Salary(2L,1200));
            repository.save(new Salary(3L,1200));
            repository.save(new Salary(4L,1500));
            repository.save(new Salary(5L,1200));
            repository.save(new Salary(6L,1200));
            repository.save(new Salary(7L,1500));
            repository.save(new Salary(8L,1200));
            repository.save(new Salary(9L,1200));
            repository.save(new Salary(10L,1500));
            repository.save(new Salary(11L,1200));
            repository.save(new Salary(12L,1200));
            repository.save(new Salary(13L,1200));
            log.info("Salaries loaded");
        };
    }*/

    @Bean
    CommandLineRunner initDatabaseLoc(LocationRepository repository) {
        return args -> {
            repository.save(new Location(1,"Corso Novara 96", 10152, "Torino", "Torino", 1));
            repository.save(new Location(2,"Via Sansovino 162", 10148, "Torino", "Torino", 1));
            log.info("Locations loaded");
        };
    }

    @Bean
    CommandLineRunner initDatabaseCou(CountryRepository repository) {
        return args -> {
            repository.save(new Country(1,"Italia",1));
            repository.save(new Country(2,"Germania",1));
            log.info("Countries loaded");
        };
    }

    @Bean
    CommandLineRunner initDatabaseReg(RegionRepository repository) {
        return args -> {
            repository.save(new Region(1,"Europa"));
            repository.save(new Region(2,"Nord America"));
            repository.save(new Region(3,"Sud America"));
            repository.save(new Region(4,"Asia"));
            log.info("Regions loaded");
        };
    }
}
