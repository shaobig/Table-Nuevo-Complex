package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadFullService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import amateur.shaobig.tnc.service.artist.ArtistProxyService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements CreateService<Album, Album>, ReadService<Album>, ReadFullService<Album>, ReadAllService<Album>, UpdateService<Album, Album> {

    private final AlbumService albumService;
    private final ArtistProxyService artistProxyService;

    @Override
    public Album create(Album album) {
        album.setArtist(getArtistProxyService().create(album.getArtist()));
        return getAlbumService().create(album);
    }

    @Override
    public Album read(Long id) {
        return getAlbumService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
    }

    @Override
    public Album readFull(Long id) {
        return getAlbumService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
    }

    @Override
    public List<Album> readAll() {
        return getAlbumService().readAll();
    }

    @Override
    public Album update(Album album) {
        return getAlbumService().update(album);
    }

}
