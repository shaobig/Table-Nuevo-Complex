package amateur.shaobig.table_nuevo_complex.transformer.album;

import amateur.shaobig.table_nuevo_complex.dto.album.GenreDto;
import amateur.shaobig.table_nuevo_complex.entity.Genre;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoTransformer implements Transformer<Genre, GenreDto> {

    @Override
    public GenreDto transform(Genre genre) {
        return new GenreDto(genre.getName(), genre.isMinor());
    }

}
