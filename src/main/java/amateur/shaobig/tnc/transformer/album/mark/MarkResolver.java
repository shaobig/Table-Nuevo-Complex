package amateur.shaobig.tnc.transformer.album.mark;

import amateur.shaobig.tnc.entity.SongMetadata;

import java.util.List;

public interface MarkResolver {

    List<Integer> resolveMarks(List<SongMetadata> metadataList);

}
