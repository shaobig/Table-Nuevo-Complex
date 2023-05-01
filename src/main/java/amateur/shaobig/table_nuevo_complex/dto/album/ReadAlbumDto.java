package amateur.shaobig.table_nuevo_complex.dto.album;

import amateur.shaobig.table_nuevo_complex.dto.song.SongDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ReadAlbumDto(Long id, String artist, int number, String name, Integer year, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time, List<SongDto> songs) {}
