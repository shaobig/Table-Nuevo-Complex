package amateur.shaobig.table_nuevo_complex.transformer.album;

import amateur.shaobig.table_nuevo_complex.dto.album.AlbumMetadataDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumMetadata;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class AlbumMetadataDtoTransformer implements Transformer<AlbumMetadata, AlbumMetadataDto> {

    @Override
    public AlbumMetadataDto transform(AlbumMetadata metadata) {
        return new AlbumMetadataDto(metadata.getTime(), metadata.isRecommendation());
    }

}
