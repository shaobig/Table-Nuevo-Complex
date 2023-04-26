package amateur.shaobig.table_nuevo_complex.service;

import amateur.shaobig.table_nuevo_complex.dto.CreateAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.transformer.CreateAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumDtoService implements CreateService<Album, CreateAlbumDto>, ReadAllService<ReadAllAlbumDto> {

    private final AlbumProxyService albumProxyService;
    private final CreateAlbumDtoTransformer createAlbumDtoTransformer;

    @Override
    public CreateAlbumDto create(Album album) {
        return getCreateAlbumDtoTransformer().transform(getAlbumProxyService().create(album));
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumProxyService().readAll();
    }

}
