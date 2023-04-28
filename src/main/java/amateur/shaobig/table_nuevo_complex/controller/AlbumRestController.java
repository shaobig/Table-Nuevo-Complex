package amateur.shaobig.table_nuevo_complex.controller;

import amateur.shaobig.table_nuevo_complex.dto.CreateAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.service.AlbumDtoService;
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
public class AlbumRestController implements CreateRestController<Album, CreateAlbumDto>, ReadRestController<ReadAlbumDto>, ReadAllRestController<ReadAllAlbumDto> {

    private final AlbumDtoService albumDtoService;

    @Override
    public ResponseEntity<CreateAlbumDto> create(Album album) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getAlbumDtoService().create(album));
    }

    @Override
    public ResponseEntity<ReadAlbumDto> read(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumDtoService().read(id));
    }

    @Override
    public ResponseEntity<List<ReadAllAlbumDto>> readAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumDtoService().readAll());
    }

}
