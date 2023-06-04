package amateur.shaobig.tnc.transformer.artist;

import amateur.shaobig.tnc.dto.artist.ReadAllArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.transformer.Transformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAllArtistDtoTransformer implements Transformer<Artist, ReadAllArtistDto> {

    private final LocationDtoTransformer locationDtoTransformer;

    @Override
    public ReadAllArtistDto transform(Artist artist) {
        LocationDto location = getLocationDtoTransformer().transform(artist.getLocation());
        return new ReadAllArtistDto(artist.getId(), artist.getName(), artist.getStatus(), location);
    }

}
