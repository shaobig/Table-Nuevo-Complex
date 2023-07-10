package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.service.CreateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class GenreProxyService implements CreateService<Genre, Genre> {

    private final GenreService genreService;

    @Override
    public Genre create(Genre genre) {
        return getGenreService().isFound(genre) ? getGenreService().merge(genre) :getGenreService().create(genre);
    }

}
