package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.service.CreateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreProxyService implements CreateService<AlbumGenre, AlbumGenre> {

    private final AlbumGenreService albumGenreService;
    private final GenreProxyService genreProxyService;

    @Override
    public AlbumGenre create(AlbumGenre albumGenre) {
        albumGenre.setGenre(getGenreProxyService().create(albumGenre.getGenre()));
        return getAlbumGenreService().create(albumGenre);
    }

}
