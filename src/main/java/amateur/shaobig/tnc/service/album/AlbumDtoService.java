package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import amateur.shaobig.tnc.transformer.album.ReadAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.album.ReadAllAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.album.UpdateAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumDtoService implements ReadService<ReadAlbumDto>, ReadAllService<ReadAllAlbumDto>, UpdateService<Album, UpdateAlbumDto> {

    private final ReadAlbumDtoTransformer readAlbumDtoTransformer;
    private final ReadAllAlbumDtoTransformer readAllAlbumDtoTransformer;
    private final UpdateAlbumDtoTransformer updateAlbumDtoTransformer;
    private final AlbumProxyService albumProxyService;

    @Override
    public ReadAlbumDto read(Long id) {
        return getReadAlbumDtoTransformer().transform(getAlbumProxyService().read(id));
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumProxyService().readAll().stream()
                .map(getReadAllAlbumDtoTransformer()::transform)
                .toList();
    }

    @Override
    public UpdateAlbumDto update(Album album) {
        return getUpdateAlbumDtoTransformer().transform(getAlbumProxyService().update(album));
    }

}
