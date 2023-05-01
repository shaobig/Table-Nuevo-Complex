package amateur.shaobig.table_nuevo_complex.transformer.artist;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.artist.ReadArtistDto;
import amateur.shaobig.table_nuevo_complex.dto.artist.location.LocationDto;
import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import amateur.shaobig.table_nuevo_complex.transformer.album.ReadAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadArtistDtoTransformer implements Transformer<Artist, ReadArtistDto> {

    private final LocationDtoTransformer locationDtoTransformer;
    private final ReadAlbumDtoTransformer readAlbumDtoTransformer;

    @Override
    public ReadArtistDto transform(Artist artist) {
        LocationDto location = getLocationDtoTransformer().transform(artist.getLocation());
        List<ReadAlbumDto> albums = artist.getAlbums().stream()
                .map(getReadAlbumDtoTransformer()::transform)
                .collect(Collectors.toList());
        return new ReadArtistDto(artist.getId(), artist.getName(), artist.getStatus(), location, albums);
    }

}
