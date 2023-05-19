package amateur.shaobig.table_nuevo_complex.service.location;

import amateur.shaobig.table_nuevo_complex.entity.Location;
import amateur.shaobig.table_nuevo_complex.repository.LocationRepository;
import amateur.shaobig.table_nuevo_complex.service.FindService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class LocationService implements FindService<Location> {

    private final LocationRepository locationRepository;

    @Override
    public Location find(Location location) {
        return getLocationRepository().getReferenceById(location.getId());
    }

}
