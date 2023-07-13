package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.UpdateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreProxyService implements CreateService<AlbumGenre, AlbumGenre>, UpdateService<AlbumGenre, AlbumGenre> {

    private final GenreProxyService genreProxyService;

    @Override
    public AlbumGenre create(AlbumGenre albumGenre) {
        albumGenre.setGenre(getGenreProxyService().create(albumGenre.getGenre()));
        return albumGenre;
    }

    @Override
    public AlbumGenre update(AlbumGenre albumGenre) {
        albumGenre.setGenre(getGenreProxyService().create(albumGenre.getGenre()));
        return albumGenre;
    }

}
