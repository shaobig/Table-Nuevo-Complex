package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.enums.AlbumType;

import java.util.List;

public record UpdateAlbumDto(Long id, int number, String name, Integer year, AlbumType type, AlbumMetadataDto metadata, List<AlbumGenreDto> genres, List<SongDto> songs) {}
