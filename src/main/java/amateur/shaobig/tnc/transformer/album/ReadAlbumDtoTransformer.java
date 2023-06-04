package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAlbumDtoTransformer implements Transformer<Album, ReadAlbumDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public ReadAlbumDto transform(Album album) {
        List<SongDto> songs = album.getSongs().stream()
                .map(getSongDtoTransformer()::transform)
                .collect(Collectors.toList());
        return new ReadAlbumDto(album.getId(), album.getArtist().getName(), album.getNumber(), album.getName(), album.getYear(), album.getType(), getAlbumMetadataDtoTransformer().transform(album.getMetadata()), songs);
    }

}
