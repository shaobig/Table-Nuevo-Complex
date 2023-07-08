package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.entity.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT album FROM Album album WHERE NOT EXISTS(SELECT 1 FROM AlbumPool albumPool WHERE albumPool.album.id = album.id)")
    @EntityGraph(value = "readAllAlbumWithSongs", type = EntityGraph.EntityGraphType.FETCH)
    List<Album> readAll();

}
