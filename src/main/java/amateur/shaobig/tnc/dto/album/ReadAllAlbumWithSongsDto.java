package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.entity.enums.AlbumType;

public record ReadAllAlbumWithSongsDto(Long id, String artist, String country, String album, AlbumType type,
                                       Integer year, AlbumStatisticsDto statistics) {
}
