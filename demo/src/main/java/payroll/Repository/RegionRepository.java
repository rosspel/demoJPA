package payroll.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payroll.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
