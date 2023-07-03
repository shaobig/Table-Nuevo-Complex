package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.entity.enums.AlbumType;

import java.time.LocalDateTime;

public record ReadAllAlbumPoolDto(Long id, Long artistId, String artistName, String country, String name,
                                  AlbumType type, Integer year, LocalDateTime time) {
}
