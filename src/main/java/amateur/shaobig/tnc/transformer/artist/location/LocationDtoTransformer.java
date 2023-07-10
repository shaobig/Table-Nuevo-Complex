package amateur.shaobig.tnc.transformer.artist.location;

import amateur.shaobig.tnc.dto.location.LocationDto;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.transformer.Transformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationDtoTransformer implements Transformer<Location, LocationDto> {

    @Override
    public LocationDto transform(Location location) {
        return new LocationDto(location.getId(), location.getCountry(), location.getRegion(), location.getLocality());
    }

}
