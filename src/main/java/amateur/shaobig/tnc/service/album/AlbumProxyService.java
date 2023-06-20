package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadFullService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements ReadService<Album>, ReadFullService<Album>, ReadAllService<Album>, UpdateService<Album, Album> {

    private final AlbumService albumService;

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
