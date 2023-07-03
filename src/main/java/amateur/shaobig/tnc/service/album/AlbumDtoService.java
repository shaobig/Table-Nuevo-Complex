package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumWithSongsDto;
import amateur.shaobig.tnc.dto.album.ReadFullAlbumDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadFullService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import amateur.shaobig.tnc.transformer.album.ReadAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.album.ReadAllAlbumWithSongsDtoTransformer;
import amateur.shaobig.tnc.transformer.album.ReadFullAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.album.UpdateAlbumDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumDtoService implements ReadService<ReadAlbumDto>, ReadFullService<ReadFullAlbumDto>, ReadAllService<ReadAllAlbumWithSongsDto>, UpdateService<Album, UpdateAlbumDto> {

    private final ReadAlbumDtoTransformer readAlbumDtoTransformer;
    private final ReadFullAlbumDtoTransformer readFullAlbumDtoTransformer;
    private final ReadAllAlbumWithSongsDtoTransformer readAllAlbumWithSongsDtoTransformer;
    private final UpdateAlbumDtoTransformer updateAlbumDtoTransformer;
    private final AlbumProxyService albumProxyService;

    @Override
    public ReadAlbumDto read(Long id) {
        return getReadAlbumDtoTransformer().transform(getAlbumProxyService().read(id));
    }

    @Override
    public ReadFullAlbumDto readFull(Long id) {
        return getReadFullAlbumDtoTransformer().transform(getAlbumProxyService().readFull(id));
    }

    @Override
    public List<ReadAllAlbumWithSongsDto> readAll() {
        return getAlbumProxyService().readAll().stream()
                .map(getReadAllAlbumWithSongsDtoTransformer()::transform)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateAlbumDto update(Album album) {
        return getUpdateAlbumDtoTransformer().transform(getAlbumProxyService().update(album));
    }

}
