package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumsDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.service.album.AlbumDtoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/album")
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumRestController implements ReadRestController<ReadAlbumDto>, ReadAllRestController<ReadAllAlbumsDto>, UpdateRestController<Album, UpdateAlbumDto> {

    private final AlbumDtoService albumDtoService;

    @Override
    public ResponseEntity<ReadAlbumDto> read(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumDtoService().read(id));
    }

    @Override
    public ResponseEntity<List<ReadAllAlbumsDto>> readAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumDtoService().readAll());
    }

    @Override
    public ResponseEntity<UpdateAlbumDto> update(Album album) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumDtoService().update(album));
    }

}
