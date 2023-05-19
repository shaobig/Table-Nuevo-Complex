package amateur.shaobig.table_nuevo_complex.controller;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.dto.pool.CreateAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.service.pool.AlbumPoolDtoService;
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
public class AlbumPoolRestController implements CreateRestController<AlbumPool, CreateAlbumPoolDto>, ReadAllRestController<ReadAllAlbumPoolDto> {

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

}
