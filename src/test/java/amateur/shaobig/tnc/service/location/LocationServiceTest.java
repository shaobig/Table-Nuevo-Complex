package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocationServiceTest {

    private LocationRepository locationRepository;

    private LocationService locationService;

    @BeforeEach
    void init() {
        this.locationRepository = Mockito.mock(LocationRepository.class);
        this.locationService = new LocationService(locationRepository);
    }

    @Test
    void mergeCheckId() {
        Location sourceLocation = new Location();
        Long sourceId = 1L;
        sourceLocation.setId(sourceId);

        locationService.merge(sourceLocation);

        Long expectedId = 1L;
        Mockito.verify(locationRepository).getReferenceById(expectedId);
    }

    @Test
    void mergeLocationWithoutPrimaryKey() {
        Location sourceLocation = new Location();
        Mockito.when(locationRepository.getReferenceById(Mockito.isNull())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> locationService.merge(sourceLocation));
    }

    @Test
    void mergeLocationNotFound() {
        Location sourceLocation = new Location();
        Long sourceId = 1L;
        sourceLocation.setId(sourceId);
        Mockito.when(locationRepository.getReferenceById(Mockito.anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> locationService.merge(sourceLocation));
    }

    @Test
    void merge() {
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME");
        Location sourceLocation = new Location(1L, "COUNTRY_NAME");
        Mockito.when(locationRepository.getReferenceById(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationService.merge(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckCountry() {
        String sourceCountry = "COUNTRY_NAME";
        Location sourceLocation = new Location(sourceCountry);

        locationService.isFound(sourceLocation);

        String expectedCountry = "COUNTRY_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.eq(expectedCountry), Mockito.any(), Mockito.any());
    }

    @Test
    void isFoundCheckRegion() {
        Location sourceLocation = new Location();
        String sourceRegion = "REGION_NAME";
        sourceLocation.setRegion(sourceRegion);

        locationService.isFound(sourceLocation);

        String expectedRegion = "REGION_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.eq(expectedRegion), Mockito.any());
    }

    @Test
    void isFoundCheckLocality() {
        Location sourceLocation = new Location();
        String sourceLocality = "LOCALITY_NAME";
        sourceLocation.setLocality(sourceLocality);

        locationService.isFound(sourceLocation);

        String expectedLocality = "LOCALITY_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocality));
    }

    static Stream<Arguments> isFoundInput() {
        Location locationWithNecessaryFields = new Location("COUNTRY_NAME");
        Location locationWithAllFields = new Location("COUNTRY_NAME", "REGION_NAME", "LOCALITY_REGION");
        Location locationWithOptionalEmptyFields = new Location("COUNTRY_NAME", "", "");

        return Stream.of(
                Arguments.of(locationWithNecessaryFields, false),
                Arguments.of(locationWithAllFields, true),
                Arguments.of(locationWithOptionalEmptyFields, true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInput")
    void isFound(Location sourceLocation, boolean expected) {
        Mockito.when(locationRepository.existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(expected);

        boolean actual = locationService.isFound(sourceLocation);

        assertEquals(expected, actual);
    }

}
