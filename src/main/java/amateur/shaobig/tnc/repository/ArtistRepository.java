package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    boolean existsByNameAndStatus(String name, ArtistStatus status);

}
