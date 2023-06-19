package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.repository.AlbumRepository;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadFullService;
import amateur.shaobig.tnc.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumService implements ReadService<Optional<Album>>, ReadFullService<Optional<Album>>, ReadAllService<Album> {

    private final AlbumRepository albumRepository;

    @Override
    public Optional<Album> read(Long id) {
        return getAlbumRepository().findById(id);
    }

    @Override
    public Optional<Album> readFull(Long id) {
        return getAlbumRepository().findById(id);
    }

    @Override
    public List<Album> readAll() {
        return getAlbumRepository().readAllWithSongs();
    }

}
