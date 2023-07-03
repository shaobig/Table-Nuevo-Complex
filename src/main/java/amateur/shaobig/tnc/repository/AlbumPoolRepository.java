package amateur.shaobig.tnc.repository;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumPoolRepository extends JpaRepository<AlbumPool, Long> {

    @Query("SELECT new amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto(" +
            "pool.album.id, pool.album.artist.id, pool.album.artist.name, pool.album.artist.location.country, pool.album.name, pool.album.type, pool.album.year, pool.album.metadata.time) " +
            "FROM AlbumPool pool")
    List<ReadAllAlbumPoolDto> readAll();

}
