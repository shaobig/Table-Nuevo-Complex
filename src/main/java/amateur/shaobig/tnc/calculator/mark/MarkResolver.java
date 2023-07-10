package amateur.shaobig.tnc.calculator.mark;

import amateur.shaobig.tnc.entity.SongMetadata;

import java.util.List;

public interface MarkResolver {

    List<Integer> resolveMarks(List<SongMetadata> metadataList);

}
