package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.SongDto;
import amateur.shaobig.table_nuevo_complex.entity.Song;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class SongDtoTransformer implements Transformer<Song, SongDto> {

    private final SongMetadataDtoTransformer songMetadataDtoTransformer;

    @Override
    public SongDto transform(Song song) {
        return new SongDto(song.getNumber(), song.getName(), getSongMetadataDtoTransformer().transform(song.getMetadata()));
    }

}
