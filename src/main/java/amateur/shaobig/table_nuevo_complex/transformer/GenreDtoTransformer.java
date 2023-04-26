package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.GenreDto;
import amateur.shaobig.table_nuevo_complex.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoTransformer implements Transformer<Genre, GenreDto> {

    @Override
    public GenreDto transform(Genre genre) {
        return new GenreDto(genre.getName(), genre.isMinor());
    }

}
