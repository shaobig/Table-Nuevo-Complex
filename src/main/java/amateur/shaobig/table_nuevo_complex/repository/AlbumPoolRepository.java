package amateur.shaobig.table_nuevo_complex.repository;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumPoolRepository extends JpaRepository<AlbumPool, Long> {

    @Query("SELECT new amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumPoolDto(" +
            "pool.album.id, pool.album.artist.name, pool.album.artist.location.country, pool.album.name, pool.album.type, pool.album.year, pool.album.metadata.time) " +
            "FROM AlbumPool pool")
    List<ReadAllAlbumPoolDto> readAll();

}
