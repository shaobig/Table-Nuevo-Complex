package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByCountryAndRegionAndLocality(String country, String region, String locality);

    Location findByCountryAndRegionAndLocality(String country, String region, String locality);

}
