package amateur.shaobig.table_nuevo_complex.dto;

import amateur.shaobig.table_nuevo_complex.entity.enums.AlbumType;

public record ReadAllAlbumDto(String artist, String country, String album, AlbumType type, int year) {}
