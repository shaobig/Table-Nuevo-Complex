package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.transformer.Transformer;
import org.springframework.stereotype.Component;

@Component
public class AlbumMetadataDtoTransformer implements Transformer<AlbumMetadata, AlbumMetadataDto> {

    @Override
    public AlbumMetadataDto transform(AlbumMetadata metadata) {
        return new AlbumMetadataDto(metadata.getTime(), metadata.isRecommendation());
    }

}
