package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class SongTypeMarkResolver implements MarkResolver {

    private final SongType songType;

    @Override
    public List<Integer> resolveMarks(List<SongMetadata> metadataList) {
        return metadataList.stream()
                .filter(metadata -> getSongType().equals(metadata.getType()))
                .map(SongMetadata::getMark)
                .toList();
    }

}
