package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationProxyServiceTest {

    private LocationService locationService;

    private LocationProxyService locationProxyService;

    @BeforeEach
    void init() {
        this.locationService = Mockito.mock(LocationService.class);

        this.locationProxyService = new LocationProxyService(locationService);
    }

    @Test
    void mergeCheckLocation() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        locationProxyService.merge(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).merge(expectedLocation);
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
        Mockito.when(locationService.merge(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationProxyService.merge(sourceLocation);

        assertEquals(expected, actual);
    }

}
