package amateur.shaobig.tnc.transformer.song.metadata;

import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class SongMetadataDtoTransformer implements Transformer<SongMetadata, SongMetadataDto> {

    @Override
    public SongMetadataDto transform(SongMetadata metadata) {
        return new SongMetadataDto(metadata.getType(), metadata.getMark());
    }

}
