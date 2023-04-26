package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.AlbumMetadataDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumMetadata;
import org.springframework.stereotype.Component;

@Component
public class AlbumMetadataDtoTransformer implements Transformer<AlbumMetadata, AlbumMetadataDto> {

    @Override
    public AlbumMetadataDto transform(AlbumMetadata metadata) {
        return new AlbumMetadataDto(metadata.getTime(), metadata.isRecommendation());
    }

}
