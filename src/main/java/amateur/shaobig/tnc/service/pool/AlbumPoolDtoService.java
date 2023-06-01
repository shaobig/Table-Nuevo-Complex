package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.DeleteService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.transformer.pool.CreateAlbumPoolDtoTransformer;
import amateur.shaobig.tnc.transformer.pool.DeleteAlbumPoolDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolDtoService implements CreateService<AlbumPool, CreateAlbumPoolDto>, ReadAllService<ReadAllAlbumPoolDto>, DeleteService<DeleteAlbumPoolDto> {

    private final AlbumPoolProxyService albumPoolProxyService;
    private final CreateAlbumPoolDtoTransformer createAlbumPoolDtoTransformer;
    private final DeleteAlbumPoolDtoTransformer deleteAlbumPoolDtoTransformer;

    @Override
    public CreateAlbumPoolDto create(AlbumPool albumPool) {
        return getCreateAlbumPoolDtoTransformer().transform(getAlbumPoolProxyService().create(albumPool));
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolProxyService().readAll();
    }

    @Override
    public DeleteAlbumPoolDto delete(Long id) {
        return getDeleteAlbumPoolDtoTransformer().transform(getAlbumPoolProxyService().delete(id));
    }

}
