package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.GenreDto;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoTransformer implements Transformer<Genre, GenreDto> {

    @Override
    public GenreDto transform(Genre genre) {
        return new GenreDto(genre.getName(), genre.isMinor());
    }

}