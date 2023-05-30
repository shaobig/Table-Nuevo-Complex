package amateur.shaobig.table_nuevo_complex.repository;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT new amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumDto(" +
            "album.id, album.artist.name, album.artist.location.country, album.name, album.type, album.year) " +
            "FROM Album album " +
            "WHERE album.id NOT IN (SELECT id FROM AlbumPool albumPool)")
    List<ReadAllAlbumDto> readAll();

}
