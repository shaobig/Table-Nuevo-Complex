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
        String sourceCountry = "COUNTRY_NAME";
        Location sourceLocation = new Location(sourceCountry);

        locationService.merge(sourceLocation);

        String expectedCountry = "COUNTRY_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.eq(expectedCountry), Mockito.any(), Mockito.any());
    }

    @Test
    void mergeCheckRegion() {
        String sourceRegion = "REGION_NAME";
        Location sourceLocation = Mockito.mock(Location.class);
        Mockito.when(sourceLocation.getRegion()).thenReturn(sourceRegion);

        locationService.merge(sourceLocation);

        String expectedRegion = "REGION_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.any(), Mockito.eq(expectedRegion), Mockito.any());
    }

    @Test
    void mergeCheckLocality() {
        String sourceLocality = "LOCALITY_NAME";
        Location sourceLocation = Mockito.mock(Location.class);
        Mockito.when(sourceLocation.getLocality()).thenReturn(sourceLocality);

        locationService.merge(sourceLocation);

        String expectedLocality = "LOCALITY_NAME";
        Mockito.verify(locationRepository).findByCountryAndRegionAndLocality(Mockito.any(), Mockito.any(), Mockito.eq(expectedLocality));
    }

    static Stream<Arguments> mergeInputData() {
        Location locationWithCountry = new Location("COUNTRY_NAME");
        Location repositoryLocationWithCountry = new Location("COUNTRY_NAME");
        Location locationWithCountryExpected = new Location("COUNTRY_NAME");

        Location locationWithCountryAndRegion = new Location("COUNTRY_NAME", "REGION_NAME", "");
        Location repositoryLocationWithCountryAndRegion = new Location("COUNTRY_NAME", "REGION_NAME", "");
        Location locationWithCountryAndRegionExpected = new Location("COUNTRY_NAME", "REGION_NAME", "");

        Location locationWithAllFields = new Location("COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location repositoryLocationWithAllFields = new Location("COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location locationWithAllFieldsExpected = new Location("COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        return Stream.of(
                Arguments.of(locationWithCountry, repositoryLocationWithCountry, locationWithCountryExpected),
                Arguments.of(locationWithCountryAndRegion, repositoryLocationWithCountryAndRegion, locationWithCountryAndRegionExpected),
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
