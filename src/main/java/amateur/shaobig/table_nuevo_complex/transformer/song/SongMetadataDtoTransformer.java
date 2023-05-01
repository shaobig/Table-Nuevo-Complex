package amateur.shaobig.table_nuevo_complex.transformer.song;

import amateur.shaobig.table_nuevo_complex.dto.song.SongMetadataDto;
import amateur.shaobig.table_nuevo_complex.entity.SongMetadata;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class SongMetadataDtoTransformer implements Transformer<SongMetadata, SongMetadataDto> {

    @Override
    public SongMetadataDto transform(SongMetadata metadata) {
        return new SongMetadataDto(metadata.getType(), metadata.getMark());
    }

}
