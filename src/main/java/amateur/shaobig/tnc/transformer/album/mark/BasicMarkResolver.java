package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class BasicMarkResolver implements MarkResolver {

    private final SongTypeMarkResolver songTypeMarkResolver;
    private final SubtractSongTypeMarkResolver subtractSongTypeMarkResolver;

    @Override
    public List<Integer> resolveMarks(List<SongMetadata> metadataList) {
        return Stream.concat(getSongTypeMarkResolver().resolveMarks(metadataList).stream(),
                getSubtractSongTypeMarkResolver().resolveMarks(metadataList).stream()).toList();
    }

}
