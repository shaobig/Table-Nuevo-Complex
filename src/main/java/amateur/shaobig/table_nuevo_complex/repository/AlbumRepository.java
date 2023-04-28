package amateur.shaobig.table_nuevo_complex.repository;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT new amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto(" +
            "album.id, album.artist.name, album.artist.location.country, album.name, album.type, album.year) " +
            "FROM Album album " +
            "WHERE album.id NOT IN (SELECT id FROM AlbumPool albumPool)")
    List<ReadAllAlbumDto> readAll();

}
