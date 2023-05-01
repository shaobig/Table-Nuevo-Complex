package amateur.shaobig.table_nuevo_complex.controller;

import amateur.shaobig.table_nuevo_complex.dto.artist.ReadArtistDto;
import amateur.shaobig.table_nuevo_complex.service.artist.ArtistDtoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artist")
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistRestController implements ReadRestController<ReadArtistDto> {

    private final ArtistDtoService artistDtoService;

    @Override
    public ResponseEntity<ReadArtistDto> read(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getArtistDtoService().read(id));
    }

}
