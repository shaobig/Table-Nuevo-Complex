package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.SongType;

import java.util.List;

public class FilteredSongTypeMarkResolver extends SongTypeMarkResolver {

    public FilteredSongTypeMarkResolver(SongType songType) {
        super(songType);
    }

    @Override
    public List<Integer> resolveMarks(List<SongMetadata> metadataList) {
        return metadataList.stream()
                .filter(metadata -> getSongType().equals(metadata.getType()))
                .map(SongMetadata::getMark)
                .toList();
    }

}
