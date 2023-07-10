package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoTransformer implements Transformer<AlbumGenre, GenreDto> {

    @Override
    public GenreDto transform(AlbumGenre albumGenre) {
        return new GenreDto(albumGenre.getId(), albumGenre.getGenre().getName(), albumGenre.isMajor());
    }

}
