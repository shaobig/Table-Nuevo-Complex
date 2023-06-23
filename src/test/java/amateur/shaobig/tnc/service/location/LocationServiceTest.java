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

    static Stream<Arguments> mergeInputData() {
        Location locationWithCountry = new Location(1L, "COUNTRY_NAME", "", "");
        Location repositoryLocationWithCountry = new Location(1L, "COUNTRY_NAME", "", "");
        Location locationWithCountryExpected = new Location(1L, "COUNTRY_NAME", "", "");

        Location locationWithAllFields = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location repositoryLocationWithAllFields = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location locationWithAllFieldsExpected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        return Stream.of(
                Arguments.of(locationWithCountry, repositoryLocationWithCountry, locationWithCountryExpected),
                Arguments.of(locationWithAllFields, repositoryLocationWithAllFields, locationWithAllFieldsExpected)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "mergeInputData")
    void merge(Location sourceLocation, Location sourceRepositoryLocation, Location expected) {
        Mockito.when(locationRepository.findByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationService.merge(sourceLocation);

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

    static Stream<Arguments> isFoundInput() {
        Location notFoundLocation = new Location();
        Location foundLocation = new Location();

        return Stream.of(
                Arguments.of(notFoundLocation, false),
                Arguments.of(foundLocation, true)
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
