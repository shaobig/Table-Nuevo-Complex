package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.enums.AlbumType;

import java.util.List;

public record ReadAlbumDto(Long id, String artist, int number, String name, Integer year, AlbumType type, AlbumMetadataDto metadata, List<SongDto> songs) {}
