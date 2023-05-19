package amateur.shaobig.table_nuevo_complex.service.pool;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.service.CreateService;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.transformer.pool.CreateAlbumPoolDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolDtoService implements CreateService<AlbumPool, CreateAlbumPoolDto>, ReadAllService<ReadAllAlbumPoolDto> {

    private final AlbumPoolProxyService albumPoolProxyService;
    private final CreateAlbumPoolDtoTransformer createAlbumPoolDtoTransformer;

    @Override
    public CreateAlbumPoolDto create(AlbumPool albumPool) {
        return getCreateAlbumPoolDtoTransformer().transform(getAlbumPoolProxyService().create(albumPool));
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolProxyService().readAll();
    }

}
