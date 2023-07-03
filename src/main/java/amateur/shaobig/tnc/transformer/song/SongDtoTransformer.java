package amateur.shaobig.tnc.transformer.song;

import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.song.metadata.SongMetadataDtoTransformer;
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
        return new SongDto(song.getId(), song.getNumber(), song.getName(), getSongMetadataDtoTransformer().transform(song.getMetadata()));
    }

}
