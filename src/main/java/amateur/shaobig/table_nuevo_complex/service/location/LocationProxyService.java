package amateur.shaobig.table_nuevo_complex.service.location;

import amateur.shaobig.table_nuevo_complex.entity.Location;
import amateur.shaobig.table_nuevo_complex.service.MergeService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationProxyService implements MergeService<Location> {

    private final LocationService locationService;

    @Override
    public Location merge(Location location) {
        return getLocationService().merge(location);
    }

}
