package amateur.shaobig.table_nuevo_complex.transformer.pool;

import amateur.shaobig.table_nuevo_complex.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.transformer.Transformer;
import amateur.shaobig.table_nuevo_complex.transformer.album.CreateAlbumDtoTransformer;
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
