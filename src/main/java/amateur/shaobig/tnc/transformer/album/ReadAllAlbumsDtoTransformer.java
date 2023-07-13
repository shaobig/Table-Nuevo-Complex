package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumsDto;
import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.album.statistics.AlbumStatisticsDtoTransformer;
import amateur.shaobig.tnc.transformer.genre.AlbumGenreDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAllAlbumsDtoTransformer implements Transformer<Album, ReadAllAlbumsDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final AlbumStatisticsDtoTransformer albumStatisticsDtoTransformer;
    private final AlbumGenreDtoTransformer albumGenreDtoTransformer;

    @Override
    public ReadAllAlbumsDto transform(Album album) {
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        AlbumStatisticsDto albumStatistics = getAlbumStatisticsDtoTransformer().transform(album);
        List<AlbumGenreDto> albumGenres = album.getGenres().stream()
                .map(getAlbumGenreDtoTransformer()::transform)
                .toList();
        return new ReadAllAlbumsDto(album.getId(), album.getName(), album.getType(), album.getYear(), album.getArtist().getId(), album.getArtist().getName(), album.getArtist().getLocation().getCountry(), albumMetadata, albumStatistics, albumGenres);
    }

}
