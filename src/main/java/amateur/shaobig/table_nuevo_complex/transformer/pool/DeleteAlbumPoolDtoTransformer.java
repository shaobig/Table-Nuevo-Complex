package amateur.shaobig.table_nuevo_complex.transformer.pool;

import amateur.shaobig.table_nuevo_complex.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import amateur.shaobig.table_nuevo_complex.transformer.album.DeleteAlbumDtoTransformer;
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
