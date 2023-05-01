package amateur.shaobig.table_nuevo_complex.dto.album;

import amateur.shaobig.table_nuevo_complex.entity.enums.AlbumType;

public record ReadAllAlbumDto(Long id, String artist, String country, String album, AlbumType type, Integer year) {}
