package amateur.shaobig.table_nuevo_complex.dto.artist;

import amateur.shaobig.table_nuevo_complex.dto.artist.location.LocationDto;
import amateur.shaobig.table_nuevo_complex.entity.enums.ArtistStatus;

public record ReadAllArtistDto(Long id, String name, ArtistStatus status, LocationDto location) {}
