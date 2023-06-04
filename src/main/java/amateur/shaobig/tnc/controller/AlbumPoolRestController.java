package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.tnc.dto.pool.DeleteAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.service.pool.AlbumPoolDtoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/album-pool")
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolRestController implements CreateRestController<AlbumPool, CreateAlbumPoolDto>, ReadAllRestController<ReadAllAlbumPoolDto>, DeleteRestController<DeleteAlbumPoolDto> {

    private final AlbumPoolDtoService albumPoolDtoService;

    @Override
    public ResponseEntity<CreateAlbumPoolDto> create(AlbumPool albumPool) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getAlbumPoolDtoService().create(albumPool));
    }

    @Override
    public ResponseEntity<List<ReadAllAlbumPoolDto>> readAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumPoolDtoService().readAll());
    }

    @Override
    public ResponseEntity<DeleteAlbumPoolDto> delete(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumPoolDtoService().delete(id));
    }

}
