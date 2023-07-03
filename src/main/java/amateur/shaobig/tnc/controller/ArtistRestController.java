package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.artist.ReadAllArtistDto;
import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.service.artist.ArtistDtoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/artist")
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistRestController implements ReadRestController<ReadArtistDto>, ReadAllRestController<ReadAllArtistDto> {

    private final ArtistDtoService artistDtoService;

    @Override
    public ResponseEntity<ReadArtistDto> read(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getArtistDtoService().read(id));
    }

    @Override
    public ResponseEntity<List<ReadAllArtistDto>> readAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getArtistDtoService().readAll());
    }

}
