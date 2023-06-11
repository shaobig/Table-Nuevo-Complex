package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.enums.SongType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
abstract class SongTypeMarkResolver implements MarkResolver {

    private SongType songType;

}
