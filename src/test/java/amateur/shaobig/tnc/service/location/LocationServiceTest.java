package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationServiceTest {

    private LocationRepository locationRepository;

    private LocationService locationService;

    @BeforeEach
    void init() {
        this.locationRepository = Mockito.mock(LocationRepository.class);

        this.locationService = new LocationService(locationRepository);
    }

    @Test
    void createCheckLocation() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        locationService.create(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationRepository).save(expectedLocation);
    }

    @Test
    void create() {
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location sourceLocation = new Location();
        Mockito.when(locationRepository.save(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationService.create(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckCountry() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "", "");

        locationService.merge(sourceLocation);

        String expectedCountry = "COUNTRY_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.eq(expectedCountry), Mockito.any(), Mockito.any());
    }

    @Test
    void mergeCheckRegion() {
        Location sourceLocation = new Location(1L, "", "REGION_NAME", "");

        locationService.merge(sourceLocation);

        String expectedRegion = "REGION_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.any(), Mockito.eq(expectedRegion), Mockito.any());
    }

    @Test
    void mergeCheckLocality() {
        Location sourceLocation = new Location(1L, "", "", "LOCALITY_NAME");

        locationService.merge(sourceLocation);

        String expectedLocality = "LOCALITY_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocality));
    }

    @Test
    void merge() {
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location sourceLocation = new Location();
        Mockito.when(locationRepository.findByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationService.merge(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void isFoundCheckCountry() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "", "");

        locationService.isFound(sourceLocation);

        String expectedCountry = "COUNTRY_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.eq(expectedCountry), Mockito.any(), Mockito.any());
    }

    @Test
    void isFoundCheckRegion() {
        Location sourceLocation = new Location(1L, "", "REGION_NAME", "");

        locationService.isFound(sourceLocation);

        String expectedRegion = "REGION_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.eq(expectedRegion), Mockito.any());
    }

    @Test
    void isFoundCheckLocality() {
        Location sourceLocation = new Location(1L, "", "", "LOCALITY_NAME");

        locationService.isFound(sourceLocation);

        String expectedLocality = "LOCALITY_NAME";
        Mockito.verify(locationRepository).existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocality));
    }

    static Stream<Arguments> isFoundInputData() {
        return Stream.of(
                Arguments.of(false),
                Arguments.of(true)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "isFoundInputData")
    void isFound(boolean expected) {
        Location sourceLocation = new Location();
        Mockito.when(locationRepository.existsByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(expected);

        boolean actual = locationService.isFound(sourceLocation);

        assertEquals(expected, actual);
    }

}
