package amateur.shaobig.table_nuevo_complex.service;

import amateur.shaobig.table_nuevo_complex.dto.CreateAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import amateur.shaobig.table_nuevo_complex.transformer.CreateAlbumDtoTransformer;
import amateur.shaobig.table_nuevo_complex.transformer.ReadAlbumDtoTransformer;
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
        Album album = getAlbumProxyService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
        return getReadAlbumDtoTransformer().transform(album);
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumProxyService().readAll();
    }

}
