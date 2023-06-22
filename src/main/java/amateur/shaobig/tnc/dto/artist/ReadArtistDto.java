package amateur.shaobig.tnc.dto.artist;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;

import java.util.List;

public record ReadArtistDto(Long id, String name, ArtistStatus status, LocationDto location, List<ReadAlbumDto> albums) {}