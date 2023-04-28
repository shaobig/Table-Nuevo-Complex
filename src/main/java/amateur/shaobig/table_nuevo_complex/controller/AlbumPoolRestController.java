package amateur.shaobig.table_nuevo_complex.controller;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.service.AlbumPoolService;
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
public class AlbumPoolRestController implements ReadAllRestController<ReadAllAlbumPoolDto> {

    private final AlbumPoolService albumPoolService;

    @Override
    public ResponseEntity<List<ReadAllAlbumPoolDto>> readAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getAlbumPoolService().readAll());
    }

}
