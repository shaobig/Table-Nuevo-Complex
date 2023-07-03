package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.repository.LocationRepository;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.FindService;
import amateur.shaobig.tnc.service.MergeService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationService implements CreateService<Location, Location>, MergeService<Location>, FindService<Location> {

    private final LocationRepository locationRepository;

    @Override
    public Location create(Location location) {
        return getLocationRepository().save(location);
    }

    @Override
    public Location merge(Location location) {
        return getLocationRepository().findByCountryAndRegionAndLocality(location.getCountry(), location.getRegion(), location.getLocality());
    }

    @Override
    public boolean isFound(Location location) {
        return getLocationRepository().existsByCountryAndRegionAndLocality(location.getCountry(), location.getRegion(), location.getLocality());
    }

}
