package amateur.shaobig.table_nuevo_complex.service.pool;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.service.CreateService;
import amateur.shaobig.table_nuevo_complex.service.DeleteService;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.artist.ArtistProxyService;
import amateur.shaobig.table_nuevo_complex.service.location.LocationProxyService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolProxyService implements CreateService<AlbumPool, AlbumPool>, ReadAllService<ReadAllAlbumPoolDto>, DeleteService<AlbumPool> {

    private final AlbumPoolService albumPoolService;
    private final ArtistProxyService artistProxyService;
    private final LocationProxyService locationProxyService;

    @Override
    public AlbumPool create(AlbumPool albumPool) {
        Artist artist = albumPool.getAlbum().getArtist();
        if (Objects.nonNull(artist.getId())) {
            artist.setLocation(getLocationProxyService().merge(artist.getLocation()));
            albumPool.getAlbum().setArtist(getArtistProxyService().merge(artist));
        }
        return getAlbumPoolService().create(albumPool);
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolService().readAll();
    }

    @Override
    public AlbumPool delete(Long id) {
        return getAlbumPoolService().delete(id);
    }

}
