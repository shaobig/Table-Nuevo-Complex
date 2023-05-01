package amateur.shaobig.table_nuevo_complex.service.album;

import amateur.shaobig.table_nuevo_complex.dto.album.CreateAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.album.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.service.CreateService;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import amateur.shaobig.table_nuevo_complex.transformer.album.CreateAlbumDtoTransformer;
import amateur.shaobig.table_nuevo_complex.transformer.album.ReadAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumDtoService implements CreateService<Album, CreateAlbumDto>, ReadService<ReadAlbumDto>, ReadAllService<ReadAllAlbumDto> {

    private final CreateAlbumDtoTransformer createAlbumDtoTransformer;
    private final ReadAlbumDtoTransformer readAlbumDtoTransformer;
    private final AlbumProxyService albumProxyService;

    @Override
    public CreateAlbumDto create(Album album) {
        return getCreateAlbumDtoTransformer().transform(getAlbumProxyService().create(album));
    }

    @Override
    public ReadAlbumDto read(Long id) {
        return getReadAlbumDtoTransformer().transform(getAlbumProxyService().read(id));
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumProxyService().readAll();
    }

}
