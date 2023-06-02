package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.DeleteService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.artist.ArtistService;
import amateur.shaobig.tnc.service.location.LocationService;
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
    private final LocationService locationService;
    private final ArtistService artistService;

    @Override
    public AlbumPool create(AlbumPool albumPool) {
        Artist artist = albumPool.getAlbum().getArtist();
        if (getLocationService().isFound(artist.getLocation())) {
            artist.setLocation(getLocationService().merge(artist.getLocation()));
        }
        if (Objects.nonNull(artist.getId())) {
            albumPool.getAlbum().setArtist(getArtistService().merge(artist));
        }
        return getAlbumPoolService().create(albumPool);
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolService().readAll();
    }

    @Override
    public AlbumPool delete(Long id) {
        return getAlbumPoolService().delete(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't delete the entity with the id = %d", id)));
    }

}