package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    void createIsFound() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        locationProxyService.create(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).isFound(expectedLocation);
    }

    @Test
    void createMerge() {
        boolean sourceIsFound = true;
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(sourceIsFound);

        locationProxyService.create(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).merge(expectedLocation);
    }

    @Test
    void createCheckLocation() {
        boolean sourceIsFound = false;
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(sourceIsFound);

        locationProxyService.create(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).create(expectedLocation);
    }

    @Test
    void createWhileLocationIsFound() {
        boolean sourceIsFound = true;
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location sourceLocation = new Location();
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(locationService.merge(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationProxyService.create(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void createWhileLocationIsNotFound() {
        boolean sourceIsFound = false;
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location sourceLocation = new Location();
        Mockito.when(locationService.isFound(Mockito.any())).thenReturn(sourceIsFound);
        Mockito.when(locationService.create(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationProxyService.create(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

    @Test
    void mergeCheckLocation() {
        Location sourceLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");

        locationProxyService.merge(sourceLocation);

        Location expectedLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Mockito.verify(locationService).merge(expectedLocation);
    }

    @Test
    void merge() {
        Location sourceRepositoryLocation = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        Location sourceLocation = new Location();
        Mockito.when(locationService.merge(Mockito.any())).thenReturn(sourceRepositoryLocation);

        Location actual = locationProxyService.merge(sourceLocation);

        Location expected = new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME");
        assertEquals(expected, actual);
    }

}
