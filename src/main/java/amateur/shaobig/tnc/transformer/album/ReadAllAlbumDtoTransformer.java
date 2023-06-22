package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAllAlbumDtoTransformer implements Transformer<Album, ReadAllAlbumDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public ReadAllAlbumDto transform(Album album) {
        return new ReadAllAlbumDto(album.getId(), album.getArtist().getName(), album.getArtist().getLocation().getCountry(), album.getName(), album.getType(), album.getYear());
    }

}
