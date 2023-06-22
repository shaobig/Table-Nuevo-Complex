package amateur.shaobig.tnc.transformer.artist;

import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.artist.location.LocationDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistDtoTransformer implements Transformer<Artist, ArtistDto> {

    private final LocationDtoTransformer locationDtoTransformer;

    @Override
    public ArtistDto transform(Artist artist) {
        return new ArtistDto(artist.getId(), artist.getName(), artist.getStatus(), getLocationDtoTransformer().transform(artist.getLocation()));
    }

}
