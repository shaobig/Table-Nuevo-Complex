package amateur.shaobig.table_nuevo_complex.dto.album;

import amateur.shaobig.table_nuevo_complex.entity.enums.AlbumType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReadAllAlbumPoolDto(Long id, String artistName, Long artistId, String country, String name, AlbumType type, Integer year, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time) {}
