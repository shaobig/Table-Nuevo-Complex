package amateur.shaobig.tnc.transformer.artist.location;

import amateur.shaobig.tnc.dto.location.LocationDto;
import amateur.shaobig.tnc.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationDtoTransformerTest {

    private LocationDtoTransformer locationDtoTransformer;

    @BeforeEach
    void init() {
        this.locationDtoTransformer = new LocationDtoTransformer();
    }

    @Test
    void transform() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        LocationDto actual = locationDtoTransformer.transform(sourceLocation);

        LocationDto expected = new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

}
