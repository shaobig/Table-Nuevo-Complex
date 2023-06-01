package amateur.shaobig.tnc.dto.artist;

import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;

public record ReadAllArtistDto(Long id, String name, ArtistStatus status, LocationDto location) {}
