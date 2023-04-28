package amateur.shaobig.table_nuevo_complex.dto;

import amateur.shaobig.table_nuevo_complex.entity.enums.AlbumType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReadAllAlbumPoolDto(Long id, String artist, String country, String album, AlbumType type, Integer year, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime time) {}
