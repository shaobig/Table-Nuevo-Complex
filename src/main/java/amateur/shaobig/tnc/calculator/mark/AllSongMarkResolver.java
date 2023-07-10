package amateur.shaobig.tnc.calculator.mark;

import amateur.shaobig.tnc.entity.SongMetadata;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllSongMarkResolver implements MarkResolver {

    @Override
    public List<Integer> resolveMarks(List<SongMetadata> metadataList) {
        return metadataList.stream()
                .map(SongMetadata::getMark)
                .toList();
    }

}
