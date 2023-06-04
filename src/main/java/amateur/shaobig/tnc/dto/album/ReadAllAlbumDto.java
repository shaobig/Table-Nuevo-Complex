package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.entity.enums.AlbumType;

public record ReadAllAlbumDto(Long id, String artist, String country, String album, AlbumType type, Integer year) {}
