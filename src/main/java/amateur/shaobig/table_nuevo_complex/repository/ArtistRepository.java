package amateur.shaobig.table_nuevo_complex.repository;

import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.entity.enums.ArtistStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    boolean existsByNameAndStatus(String name, ArtistStatus status);

}
