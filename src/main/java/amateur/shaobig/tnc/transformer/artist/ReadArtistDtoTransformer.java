package amateur.shaobig.tnc.transformer.artist;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.ReadAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.artist.location.LocationDtoTransformer;
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
