package amateur.shaobig.table_nuevo_complex.transformer.artist;

import amateur.shaobig.table_nuevo_complex.dto.artist.location.LocationDto;
import amateur.shaobig.table_nuevo_complex.entity.Location;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
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
        return new LocationDto(location.getCountry(), location.getRegion(), location.getLocality());
    }

}
