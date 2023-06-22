package amateur.shaobig.tnc.transformer.album.statistics;

import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.transformer.Transformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumStatisticsDtoTransformer implements Transformer<Album, AlbumStatisticsDto> {

    private final SongMetadataListAlbumStatisticsDtoTransformer songMetadataListAlbumStatisticsDtoTransformer;

    @Override
    public AlbumStatisticsDto transform(Album album) {
        List<SongMetadata> metadataList = album.getSongs().stream()
                .map(Song::getMetadata)
                .toList();
        return getSongMetadataListAlbumStatisticsDtoTransformer().transform(metadataList);
    }

}
