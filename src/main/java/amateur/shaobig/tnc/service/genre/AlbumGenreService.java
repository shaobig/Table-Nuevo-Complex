package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.repository.AlbumGenreRepository;
import amateur.shaobig.tnc.service.CreateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreService implements CreateService<AlbumGenre, AlbumGenre> {

    private final AlbumGenreRepository albumGenreRepository;

    @Override
    public AlbumGenre create(AlbumGenre albumGenre) {
        return getAlbumGenreRepository().save(albumGenre);
    }

}
