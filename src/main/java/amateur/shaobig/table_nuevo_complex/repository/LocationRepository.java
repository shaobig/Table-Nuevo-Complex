package amateur.shaobig.table_nuevo_complex.repository;

import amateur.shaobig.table_nuevo_complex.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByCountryAndRegionAndLocality(String country, String region, String locality);

}
