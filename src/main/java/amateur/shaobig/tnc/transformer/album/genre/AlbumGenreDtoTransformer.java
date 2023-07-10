package amateur.shaobig.tnc.transformer.album.genre;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class AlbumGenreDtoTransformer implements Transformer<AlbumGenre, AlbumGenreDto> {

    @Override
    public AlbumGenreDto transform(AlbumGenre albumGenre) {
        return new AlbumGenreDto(albumGenre.getId(), albumGenre.getGenre().getName(), albumGenre.isMajor());
    }

}
