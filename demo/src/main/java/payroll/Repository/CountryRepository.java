package payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payroll.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
