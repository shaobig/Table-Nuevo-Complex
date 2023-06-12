package amateur.shaobig.tnc.dto.album;

import java.math.BigDecimal;

public record AlbumStatisticsDto(BigDecimal fullMark, BigDecimal basicMark, BigDecimal averageMark, BigDecimal sum, BigDecimal finalMark) {}
