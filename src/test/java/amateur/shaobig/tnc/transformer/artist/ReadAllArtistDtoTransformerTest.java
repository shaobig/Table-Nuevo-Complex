package amateur.shaobig.tnc.transformer.artist;

import amateur.shaobig.tnc.dto.artist.ReadAllArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.transformer.artist.location.LocationDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadAllArtistDtoTransformerTest {

    private LocationDtoTransformer locationDtoTransformer;

    private ReadAllArtistDtoTransformer readAllArtistDtoTransformer;

    @BeforeEach
    void init() {
        this.locationDtoTransformer = Mockito.mock(LocationDtoTransformer.class);

        this.readAllArtistDtoTransformer = new ReadAllArtistDtoTransformer(locationDtoTransformer);
    }

    @Test
    void transformCheckArtistLocation() {
        Artist sourceArtist = new Artist(1L, "", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());

        readAllArtistDtoTransformer.transform(sourceArtist);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationDtoTransformer).transform(expectedLocation);
    }

    @Test
    void transform() {
        LocationDto sourceLocationDto = new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Artist sourceArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Mockito.when(locationDtoTransformer.transform(Mockito.any())).thenReturn(sourceLocationDto);

        ReadAllArtistDto actual = readAllArtistDtoTransformer.transform(sourceArtist);

        ReadAllArtistDto expected = new ReadAllArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"));
        assertEquals(expected, actual);
    }

}
