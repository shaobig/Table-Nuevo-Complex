package amateur.shaobig.table_nuevo_complex.service.location;

import amateur.shaobig.table_nuevo_complex.entity.Location;
import amateur.shaobig.table_nuevo_complex.repository.LocationRepository;
import amateur.shaobig.table_nuevo_complex.service.FindService;
import amateur.shaobig.table_nuevo_complex.service.MergeService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationService implements MergeService<Location>, FindService<Location> {

    private final LocationRepository locationRepository;

    @Override
    public Location merge(Location location) {
        return getLocationRepository().getReferenceById(location.getId());
    }

    @Override
    public boolean isFound(Location location) {
        return getLocationRepository().existsByCountryAndRegionAndLocality(location.getCountry(), location.getRegion(), location.getLocality());
    }

}
