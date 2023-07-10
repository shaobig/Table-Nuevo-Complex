package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.repository.GenreRepository;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.FindService;
import amateur.shaobig.tnc.service.MergeService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class GenreService implements CreateService<Genre, Genre>, FindService<Genre>, MergeService<Genre> {

    private final GenreRepository genreRepository;

    @Override
    public Genre create(Genre genre) {
        return getGenreRepository().save(genre);
    }

    @Override
    public boolean isFound(Genre genre) {
        return getGenreRepository().existsByName(genre.getName());
    }

    @Override
    public Genre merge(Genre genre) {
        return getGenreRepository().findByName(genre.getName());
    }

}
