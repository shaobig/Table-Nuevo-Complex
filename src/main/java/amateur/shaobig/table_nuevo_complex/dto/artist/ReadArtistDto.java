package amateur.shaobig.table_nuevo_complex.dto.artist;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.artist.location.LocationDto;
import amateur.shaobig.table_nuevo_complex.entity.enums.ArtistStatus;

import java.util.List;

public record ReadArtistDto(Long id, String name, ArtistStatus status, LocationDto location, List<ReadAlbumDto> albums) {}
