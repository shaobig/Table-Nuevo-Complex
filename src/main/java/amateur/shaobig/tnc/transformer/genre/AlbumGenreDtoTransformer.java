package amateur.shaobig.tnc.transformer.genre;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.transformer.Transformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreDtoTransformer implements Transformer<AlbumGenre, AlbumGenreDto> {

    private final GenreDtoTransformer genreDtoTransformer;

    @Override
    public AlbumGenreDto transform(AlbumGenre albumGenre) {
        return new AlbumGenreDto(albumGenre.getId(), albumGenre.isMajor(), getGenreDtoTransformer().transform(albumGenre.getGenre()));
    }

}
