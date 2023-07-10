package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.entity.enums.AlbumType;

public record ReadAllAlbumDto(Long id, String name, AlbumType type, Integer year, Long artistId, String artistName,
                              String country, AlbumStatisticsDto statistics) {
}
