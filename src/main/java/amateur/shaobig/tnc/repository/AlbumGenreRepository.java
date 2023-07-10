package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.entity.AlbumGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumGenreRepository extends JpaRepository<AlbumGenre, Long> {

}
