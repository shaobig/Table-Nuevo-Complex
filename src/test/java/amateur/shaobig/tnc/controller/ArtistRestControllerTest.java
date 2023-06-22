package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.artist.ReadAllArtistDto;
import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.service.artist.ArtistDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

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

    @Test
    void readAll() {
        List<ReadAllArtistDto> sourceResponseBody = List.of(new ReadAllArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", "")));
        Mockito.when(artistDtoService.readAll()).thenReturn(sourceResponseBody);

        ResponseEntity<List<ReadAllArtistDto>> actual = artistRestController.readAll();

        List<ReadAllArtistDto> expectedResponseBody = List.of(new ReadAllArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", "")));
        ResponseEntity<List<ReadAllArtistDto>> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void findCheckArtist() {
        Artist sourceArtist = new Artist("ARTIST_NAME", new Location("", "", ""));

        artistRestController.find(sourceArtist);

        Artist expectedArtist = new Artist("ARTIST_NAME", new Location("", "", ""));
        Mockito.verify(artistDtoService).isFound(expectedArtist);
    }

    static Stream<Arguments> findInputData() {
        ResponseEntity<Void> foundResponseEntity = ResponseEntity.status(HttpStatus.FOUND).build();
        ResponseEntity<Void> notFoundResponseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return Stream.of(
                Arguments.of(true, foundResponseEntity),
                Arguments.of(false, notFoundResponseEntity)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "findInputData")
    void find(boolean sourceIsArtistFound, ResponseEntity<Void> expected) {
        Artist sourceArtist = new Artist();
        Mockito.when(artistDtoService.isFound(Mockito.any())).thenReturn(sourceIsArtistFound);

        ResponseEntity<Void> actual = artistRestController.find(sourceArtist);

        assertEquals(expected, actual);
    }

}
