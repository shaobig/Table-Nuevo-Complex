package amateur.shaobig.tnc.dto.album;

import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.enums.AlbumType;

import java.util.List;

public record ReadFullAlbumDto(Long id, Integer number, String name, Integer year, AlbumType type,
                               AlbumMetadataDto metadata, ArtistDto artist, List<GenreDto> genres,
                               List<SongDto> songs) {
}
