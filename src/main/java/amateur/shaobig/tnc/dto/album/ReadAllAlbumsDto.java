package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.entity.enums.AlbumType;

import java.util.List;

public record ReadAllAlbumsDto(Long id, String name, AlbumType type, Integer year, Long artistId, String artistName,
                               String country, AlbumMetadataDto metadata, AlbumStatisticsDto statistics,
                               List<AlbumGenreDto> genres) {
}
