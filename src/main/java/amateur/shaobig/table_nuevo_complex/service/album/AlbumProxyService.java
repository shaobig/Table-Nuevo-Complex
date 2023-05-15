package amateur.shaobig.table_nuevo_complex.service.album;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import amateur.shaobig.table_nuevo_complex.service.CreateService;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import amateur.shaobig.table_nuevo_complex.service.artist.ArtistProxyService;
import amateur.shaobig.table_nuevo_complex.service.location.LocationProxyService;
import amateur.shaobig.table_nuevo_complex.service.pool.AlbumPoolService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements CreateService<Album, Album>, ReadService<Album>, ReadAllService<ReadAllAlbumDto> {

    private final AlbumPoolService albumPoolService;
    private final AlbumService albumService;
    private final ArtistProxyService artistProxyService;
    private final LocationProxyService locationProxyService;

    @Override
    public Album create(Album album) {
        if (Objects.nonNull(album.getArtist().getId())) {
            if (Objects.nonNull(album.getArtist().getLocation().getId())) {
                album.getArtist().setLocation(getLocationProxyService().find(album.getArtist().getLocation()));
            }
            album.setArtist(getArtistProxyService().find(album.getArtist()));
        }
        return getAlbumPoolService().create(new AlbumPool(album)).getAlbum();
    }

    @Override
    public Album read(Long id) {
        return getAlbumService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumService().readAll();
    }

}
