package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumWithSongsDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.album.statistics.AlbumStatisticsDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAllAlbumWithSongsDtoTransformer implements Transformer<Album, ReadAllAlbumWithSongsDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;
    private final AlbumStatisticsDtoTransformer albumStatisticsDtoTransformer;

    @Override
    public ReadAllAlbumWithSongsDto transform(Album album) {
        return new ReadAllAlbumWithSongsDto(album.getId(), album.getArtist().getName(), album.getArtist().getLocation().getCountry(), album.getName(), album.getType(), album.getYear(), getAlbumStatisticsDtoTransformer().transform(album));
    }

}
