package amateur.shaobig.tnc.dto.song;

import amateur.shaobig.tnc.entity.enums.SongType;

public record SongMetadataDto(SongType type, byte mark) {}
