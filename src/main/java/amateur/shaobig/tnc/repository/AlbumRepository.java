package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Deprecated(forRemoval = true)
    @Query("SELECT new amateur.shaobig.tnc.dto.album.ReadAllAlbumDto(" +
            "album.id, album.artist.name, album.artist.location.country, album.name, album.type, album.year) " +
            "FROM Album album " +
            "WHERE album.id NOT IN (SELECT id FROM AlbumPool albumPool)")
    List<ReadAllAlbumDto> readAll();

    @Query("SELECT album FROM Album album WHERE NOT EXISTS(SELECT 1 FROM AlbumPool albumPool WHERE albumPool.album.id = album.id)")
    @EntityGraph(value = "readAllAlbumWithSongs", type = EntityGraph.EntityGraphType.FETCH)
    List<Album> readAllWithSongs();

}
