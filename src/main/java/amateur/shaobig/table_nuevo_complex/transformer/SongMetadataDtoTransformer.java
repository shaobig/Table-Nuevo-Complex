package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.SongMetadataDto;
import amateur.shaobig.table_nuevo_complex.entity.SongMetadata;
import org.springframework.stereotype.Component;

@Component
public class SongMetadataDtoTransformer implements Transformer<SongMetadata, SongMetadataDto> {

    @Override
    public SongMetadataDto transform(SongMetadata metadata) {
        return new SongMetadataDto(metadata.getType(), metadata.getMark());
    }

}
