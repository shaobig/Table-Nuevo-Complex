package amateur.shaobig.tnc.dto.album;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AlbumMetadataDto(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy (HH:mm)") LocalDateTime time, boolean isRecommendation) {}
