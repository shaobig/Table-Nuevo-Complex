package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.service.artist.ArtistDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArtistRestControllerTest {

    private ArtistDtoService artistDtoService;

    private ArtistRestController artistRestController;

    @BeforeEach
    void init() {
        this.artistDtoService = Mockito.mock(ArtistDtoService.class);

        this.artistRestController = new ArtistRestController(artistDtoService);
    }

    @Test
    void readCheckArtistId() {
        Long sourceId = 1L;

        artistRestController.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(artistDtoService).read(expectedId);
    }

    @Test
    void read() {
        ReadArtistDto sourceResponseBody = new ReadArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", ""), List.of());
        Long sourceId = 1L;
        Mockito.when(artistDtoService.read(Mockito.anyLong())).thenReturn(sourceResponseBody);

        ResponseEntity<ReadArtistDto> actual = artistRestController.read(sourceId);

        ReadArtistDto expectedResponseBody = new ReadArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", ""), List.of());
        ResponseEntity<ReadArtistDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

}
