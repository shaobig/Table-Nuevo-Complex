package amateur.shaobig.tnc.transformer.album.calculator.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class SubtractSongTypeMarkResolver implements MarkResolver {

    private final SongTypeMarkResolver songTypeMarkResolver;
    private final int subtractFactor;

    @Override
    public List<Integer> resolveMarks(List<SongMetadata> metadataList) {
        return getSongTypeMarkResolver().resolveMarks(metadataList).stream()
                .map(mark -> mark - getSubtractFactor())
                .toList();
    }

}
