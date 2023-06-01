package amateur.shaobig.tnc.transformer.pool;

import amateur.shaobig.tnc.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.CreateAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class CreateAlbumPoolDtoTransformer implements Transformer<AlbumPool, CreateAlbumPoolDto> {

    private final CreateAlbumDtoTransformer createAlbumDtoTransformer;

    @Override
    public CreateAlbumPoolDto transform(AlbumPool albumPool) {
        return new CreateAlbumPoolDto(getCreateAlbumDtoTransformer().transform(albumPool.getAlbum()));
    }

}
