package amateur.shaobig.tnc.service.location;

import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.MergeService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationProxyService implements CreateService<Location, Location>, MergeService<Location> {

    private final LocationService locationService;

    @Override
    public Location create(Location location) {
        return getLocationService().isFound(location) ? getLocationService().merge(location) : getLocationService().create(location);
    }

    @Override
    public Location merge(Location location) {
        return getLocationService().merge(location);
    }

}
