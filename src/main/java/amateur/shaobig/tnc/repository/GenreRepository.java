package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsByName(String name);

    Genre findByName(String name);

}
