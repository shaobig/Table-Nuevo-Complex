package amateur.shaobig.table_nuevo_complex.dto;

import amateur.shaobig.table_nuevo_complex.entity.enums.AlbumType;

import java.util.List;

public record CreateAlbumDto(Long id, int number, String name, Integer year, AlbumType type, AlbumMetadataDto metadata, List<GenreDto> genres, List<SongDto> songs) {}
