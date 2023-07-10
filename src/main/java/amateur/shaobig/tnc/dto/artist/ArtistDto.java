package amateur.shaobig.tnc.dto.artist;

import amateur.shaobig.tnc.dto.location.LocationDto;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;

public record ArtistDto(Long id, String name, ArtistStatus status, LocationDto location) {}
