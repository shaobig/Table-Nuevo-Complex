package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoTransformer implements Transformer<Genre, GenreDto> {

    @Override
    public GenreDto transform(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName(), genre.isMajor());
    }

}
