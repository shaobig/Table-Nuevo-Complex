package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.repository.AlbumRepository;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumService implements CreateService<Album, Album>, ReadService<Optional<Album>>, ReadAllService<Album>, UpdateService<Album, Album> {

    private final AlbumRepository albumRepository;

    @Override
    public Album create(Album album) {
        return getAlbumRepository().save(album);
    }

    @Override
    public Optional<Album> read(Long id) {
        return getAlbumRepository().findById(id);
    }

    @Override
    public List<Album> readAll() {
        return getAlbumRepository().readAllWithSongs();
    }

    @Override
    public Album update(Album album) {
        return getAlbumRepository().save(album);
    }

}
