package payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payroll.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
