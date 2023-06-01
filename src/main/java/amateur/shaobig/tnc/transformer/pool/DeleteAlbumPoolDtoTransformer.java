package amateur.shaobig.tnc.transformer.pool;

import amateur.shaobig.tnc.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.DeleteAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class DeleteAlbumPoolDtoTransformer implements Transformer<AlbumPool, DeleteAlbumPoolDto> {

    private final DeleteAlbumDtoTransformer deleteAlbumDtoTransformer;

    @Override
    public DeleteAlbumPoolDto transform(AlbumPool albumPool) {
        return new DeleteAlbumPoolDto(getDeleteAlbumDtoTransformer().transform(albumPool.getAlbum()));
    }

}
